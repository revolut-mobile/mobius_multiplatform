//
//  ViewControllerAssembly.swift
//  Mobius Test App
//
//  Created by Иван Важнов on 25/03/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import Foundation
import EasyDi
import common

class ViewControllerAssembly: Assembly {
    
    lazy var serviceAssembly: ServiceAssembly = self.context.assembly()
    
    func inject(into viewController: ViewController) {
        defineInjection(into: viewController) {
            $0.presenter = ExchangePresenter(
                interactor: self.serviceAssembly.exchangeInteractor,
                UI: UI()
                    
            )
            return $0
        }
    }
}
