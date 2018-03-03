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
    
    private func dispatchResume(block: () -> Void) {
        print("before")
        let _ = block()
        print("after")
    }
    
    override func dispatchResume(value: Any?, continuation: RevStdlibContinuation) -> Bool {
//        dispatchResume {
            continuation.resume(value: value)
//        }
        return true
    }
    
    override func dispatchResumeWithException(exception: RevStdlibThrowable, continuation: RevStdlibContinuation) -> Bool {
        dispatchResume {
            continuation.resumeWithException(exception: exception)
        }
        return true
    }
    
    //    override func dispatch(context: RevStdlibCoroutineContext, block: RevRunnable) {
    //        print("11111")
    //        DispatchQueue.global(qos: .background).async {
    //            block.run()
    //
    //            DispatchQueue.main.async {
    //                print("This is run on the main queue, after the previous code in outer block")
    //            }
    //        }
    //    }
    
}

class Key: NSObject, RevStdlibCoroutineContextKey {
    
}

class ViewController: UIViewController, RevCardsView {
    
    private lazy var presenter: RevCardsPresenter = {
        let key = Key()
        let context = Context()
        return RevCardsPresenter(dispatcher: context)
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

