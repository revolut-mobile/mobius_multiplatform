//
//  Ticker.swift
//  Mobius Test App
//
//  Created by Иван Важнов on 24/03/2018.
//  Copyright © 2018 Revolut. All rights reserved.
//

import Foundation
import SharedCode

class Ticker {
    let title: String
    let subTitle: String
    
    init(market: Market, ticker: SharedCode.Ticker) {
        self.title = "\(market.marketName)"
        self.subTitle = "Bid: \(ticker.bid.string(fractionDigits: 8)), Ask: \(ticker.ask.string(fractionDigits: 8))"
    }
}

fileprivate extension Double {
    func string(fractionDigits:Int) -> String {
        let formatter = NumberFormatter()
        formatter.minimumFractionDigits = fractionDigits
        formatter.maximumFractionDigits = fractionDigits
        return formatter.string(for: self) ?? "\(self)"
    }
}
