//
//  Bittrex.swift
//  Mobius Test App
//
//  Created by Иван Важнов on 24/03/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import Foundation
import Alamofire
import SwiftyJSON
import Rev

class BittrexApi: NSObject {
    
    func getAllMarkets(callback: RevStdlibContinuation) {
        Alamofire.request("https://bittrex.com/api/v1.1/public/getmarkets")
            .debugLog()
            .responseJSON { response in
                if let result = response.result.value {
                    let jsonResult = JSON(result)
                    let list = jsonResult["result"].arrayValue.map({json -> RevMarket in
                        return RevMarket(
                            marketCurrency: json["MarketCurrency"].stringValue,
                            marketCurrencyLong: json["MarketCurrencyLong"].stringValue,
                            baseCurrency: json["BaseCurrency"].stringValue,
                            baseCurrencyLong: json["BaseCurrencyLong"].stringValue,
                            minTradeSize: json["MinTradeSize"].doubleValue,
                            marketName: json["MarketName"].stringValue,
                            isActive: json["IsActive"].boolValue,
                            created: json["Created"].stringValue,
                            logoUrl: json["LogoUrl"].string
                        )
                    })
                    
                    callback.resume(value: list)
                }
        }
    }
    
    func getTicker(market: RevMarket, callback: RevStdlibContinuation) {
        Alamofire.request("https://bittrex.com/api/v1.1/public/getticker?market=\(market.marketName)")
            .debugLog()
            .responseJSON { response in
                if let result = response.result.value {
                    let jsonResult = JSON(result)
                    let dic = jsonResult["result"].dictionaryValue
                    let list = RevTicker(
                            bid: dic["Bid"]!.doubleValue,
                            ask: dic["Ask"]!.doubleValue,
                            last: dic["Last"]!.doubleValue
                        )
                    callback.resume(value: list)
                }
        }
    }
    
}


fileprivate extension Request {
    func debugLog() -> Self {
        #if DEBUG
            debugPrint(self)
        #endif
        return self
    }
}
