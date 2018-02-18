//
//  ViewController.swift
//  mobius
//
//  Created by Ivan Vazhnov on 18/02/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import UIKit
import Rev

class ViewController: UIViewController {
    
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

