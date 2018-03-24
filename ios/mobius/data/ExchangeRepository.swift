//
//  ExchangeRepository.swift
//  Mobius Test App
//
//  Created by Иван Важнов on 24/03/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import Foundation
import Rev

class ExchangeRepository: RevExchangeRepository {
    
    private let api: BittrexApi
    
    init(api: BittrexApi) {
        self.api = api
    }
    
    override func getAllMarkets(callback: RevStdlibContinuation) {
        api.getAllMarkets(callback: callback)
    }
    
    override func getTicker(market: RevMarket, callback: RevStdlibContinuation) {
        api.getTicker(market: market, callback: callback)
    }
    
}
