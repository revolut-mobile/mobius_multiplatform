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

class BittrexApi {
    
    var baseURL: URL?
    
    func getAllMarkets(callback: RevStdlibContinuation) {
        guard let baseURL = self.baseURL else {
            callback.resumeWithException(exception: RevStdlibThrowable(message: "Can't get url"))
            return
        }
        
        let url = baseURL.appendingPathComponent("getmarkets")
        Alamofire.request(url)
            //.debugLog()
            .responseJSON { response in
                if let result = MapperFactory.marketsMapper.map(response: response) {
                    callback.resume(value: result)
                } else {
                    callback.resumeWithException(exception: RevStdlibThrowable(message: "Can't parse response"))
                }
        }
    }
    
    func getTicker(market: RevMarket, callback: RevStdlibContinuation) {
        guard let baseURL = self.baseURL else {
            callback.resumeWithException(exception: RevStdlibThrowable(message: "Can't get url"))
            return
        }
        
        let url = baseURL
            .appendingPathComponent("getticker")
            .addQueryParams(newParams: [URLQueryItem(name: "market", value: market.marketName)])
        
        Alamofire.request(url)
//            .debugLog()
            .responseJSON { response in
                if let result = MapperFactory.tickersMapper.map(response: response) {
                    callback.resume(value: result)
                } else {
                    callback.resumeWithException(exception: RevStdlibThrowable(message: "Can't parse response"))
                }
        }
    }
    
}

fileprivate extension URL {
    func addQueryParams(newParams: [URLQueryItem]) -> URL {
        let urlComponents = NSURLComponents.init(url: self, resolvingAgainstBaseURL: false)
        if (urlComponents?.queryItems == nil) {
            urlComponents!.queryItems = []
        }
        
        urlComponents!.queryItems!.append(contentsOf: newParams)
        return urlComponents!.url!
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
