//
//  ViewController.swift
//  mobius
//
//  Created by Ivan Vazhnov on 18/02/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import UIKit
import Rev

class Context : RevContinuationDispatcher {
    
    override func dispatchResume(value: Any?, continuation: RevStdlibContinuation) -> Bool {
        DispatchQueue.global(qos: .background).async {
            print("2222")
            continuation.resume(value: value)
        }
        return true
    }
    
    override func dispatchResumeWithException(exception: RevStdlibThrowable, continuation: RevStdlibContinuation) -> Bool {
        DispatchQueue.global(qos: .background).async {
            continuation.resumeWithException(exception: exception)
        }
        return true
    }
}

class StringGetter: NSObject,RevGetter {
    func getString() -> String {
        return " World"
    }
}

class ViewController: UIViewController, RevCardsView {
    
    private lazy var presenter: RevCardsPresenter = {
        let getter = StringGetter()
        return RevCardsPresenter(dispatcher: Context(), getter: getter)
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

