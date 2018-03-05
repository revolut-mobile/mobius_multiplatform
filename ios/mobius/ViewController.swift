//
//  ViewController.swift
//  mobius
//
//  Created by Ivan Vazhnov on 18/02/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import UIKit
import Rev


class CardsRepository: RevCardsRepository{
    
    override func getAllCardsSync(callback: @escaping ([RevRevolutCard]) -> RevStdlibUnit) {
        DispatchQueue.global(qos: .background).async {
            let result = [1, 2, 3, 4, 5].map( { i -> RevRevolutCard in
                usleep(1_000_000)
                print("processing card \(i) on thread is main \(Thread.current.isMainThread)")
                return RevRevolutCardImpl(id: "id.\(i)")
            })
            DispatchQueue.main.async {
                let _ = callback(result)
            }
        }
    }
}

class ViewController: UIViewController, RevCardsView {
    
    private lazy var presenter: RevCardsPresenter = {
        let interactor = RevCardsInteractor(cardsRepository: CardsRepository())
        return RevCardsPresenter(
            workerContext: RevAsyncDispatcher(),
            uiContext: RevMainQueueDispatcher(),
            interactor: interactor
        )
    }()
    
    func showCard(list: [RevRevolutCard]) {
        print("cards received on thread is main \(Thread.current.isMainThread)")
        for card in list {
            print("Card.id = " + card.id)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.attach(view: self)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        presenter.detach()
    }
    
    @IBAction func onClick(_ sender: Any) {
        print("Click")
        presenter.start()
        //        let item = RevRevolutCardImpl(id: "id.1")
        //        item.printIdAsync()
        //        item.runAsync(l: { () -> RevStdlibUnit in
        //            print("This is background thread")
        //            return RevStdlibUnit()
        //
        //        })
        //        print("First")
    }
    
}

