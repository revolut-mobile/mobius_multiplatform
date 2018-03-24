//
//  ViewController.swift
//  mobius
//
//  Created by Ivan Vazhnov on 18/02/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import UIKit
import Rev

class ViewController: UITableViewController, RevExchangeView {
    
    weak var activityIndicatorView: UIActivityIndicatorView?
    var items = [Ticker]()
    
    private lazy var presenter = RevExchangePresenter(
        uiContext: RevMainQueueDispatcher(),
        allMarketsTickersInteractor: TickersInteractor(exchangeRepository: ExchangeRepository(api: BittrexApi()))
    )
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let activityIndicatorView = UIActivityIndicatorView(activityIndicatorStyle: UIActivityIndicatorViewStyle.gray)
        tableView.backgroundView = activityIndicatorView
        tableView.separatorStyle = UITableViewCellSeparatorStyle.none
        self.activityIndicatorView = activityIndicatorView
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "activityIndicator")
        presenter.attach(view: self)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        presenter.detach()
    }
    
    func showMarketTickers(tickers: [RevMarket : RevTicker]) {
        items.removeAll()
        items += tickers.map { (arg) -> Ticker in
            return Ticker(market: arg.key, ticker: arg.value)
        }
        tableView.reloadData()
    }
    
    func showLoading(loading: Bool) {
        if (loading) {
            self.activityIndicatorView?.startAnimating()
        } else {
            self.activityIndicatorView?.stopAnimating()
        }
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

