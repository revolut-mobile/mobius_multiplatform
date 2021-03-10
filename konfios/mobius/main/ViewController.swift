//
//  ViewController.swift
//  mobius
//
//  Created by Ivan Vazhnov on 18/02/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import UIKit
import SharedCode

class ViewController: UITableViewController, ExchangeView {
    
    var presenter: ExchangePresenter!
    var items = [Ticker]()
    
    override func awakeFromNib() {
        super.awakeFromNib()
        ViewControllerAssembly.instance().inject(into: self)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.separatorStyle = .none
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        presenter.attach(view: self)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        presenter.detach()
    }
    
    func showMarkets(tickers: [KotlinPair<SharedCode.Market, SharedCode.Ticker>]) {
        print("showMarkets on thread is \(Thread.current)")
        items.removeAll()
        items += tickers.map { (arg) -> Ticker in
            return Ticker(market: arg.first!, ticker: arg.second!)
        }
        tableView.reloadData()
    }
    
    func showLoading(loading: Bool) {
        if (loading) {
            refreshControl?.beginRefreshing()
            refreshControl?.isHidden = false
        } else {
            refreshControl?.endRefreshing()
        }
    }
    
    @IBAction func refresh(refreshControl: UIRefreshControl) {
        //presenter.refresh()
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TitleCell", for: indexPath)
        let ticker = items[indexPath.item]
        cell.textLabel?.text = ticker.title
        cell.detailTextLabel?.text = ticker.subTitle
        return cell
    }
    
    
}

