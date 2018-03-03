//
//  ViewController.swift
//  mobius
//
//  Created by Ivan Vazhnov on 18/02/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import UIKit
import Rev

class Context : NSObject, RevStdlibCoroutineContext {
    
    func fold(initial: Any?, operation: @escaping (Any?, RevStdlibCoroutineContextElement) -> Any?) -> Any? {
        return nil
    }
    
    func get(key: RevStdlibCoroutineContextKey) -> RevStdlibCoroutineContextElement? {
        return nil
    }
    
    func minusKey(key: RevStdlibCoroutineContextKey) -> RevStdlibCoroutineContext {
        return self
    }
    
    func plus(context: RevStdlibCoroutineContext) -> RevStdlibCoroutineContext {
        return self
    }

    

    
}

class ViewController: UIViewController, RevCardsView {
    
    private lazy var presenter: RevCardsPresenter = {
        let context = Context()
//        let repository = RevCardsRepository()
//        let interactor = RevCardsInteractor(cardsRepository: repository)
        return RevCardsPresenter(context: context)
    }()

    func showCard(list: [RevRevolutCard]) {
        
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
        let item = RevRevolutCardImpl(id: "id.1")
        item.printIdAsync()
        item.runAsync(l: { () -> RevStdlibUnit in
            print("This is background thread")
            return RevStdlibUnit()

        })
        print("First")
    }
    
}

