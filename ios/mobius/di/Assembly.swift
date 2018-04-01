//
//  Assembly.swift
//  Mobius Test App
//
//  Created by Иван Важнов on 25/03/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import Foundation
import EasyDi
import Rev

class ServiceAssembly: Assembly {
    
    var api: BittrexApi {
        return define(scope: .lazySingleton, init: BittrexApi()) {
            $0.baseURL = URL(string: "https://bittrex.com/api/v1.1/public")
            return $0
        }
    }

    var exchangeRepository: RevExchangeRepository {
        return define(init: ExchangeRepository(api: self.api))
    }
    
    var exchangeInteractor: RevAllMarketsTickersInteractor {
        return define(init: RevAllMarketsTickersSimultaneousInteractor(
            exchangeRepository: self.exchangeRepository,
            workerContext: RevAsyncDispatcher()
        ))
    }
        
}
