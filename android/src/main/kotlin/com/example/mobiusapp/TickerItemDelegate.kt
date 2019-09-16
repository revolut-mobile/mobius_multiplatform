package com.example.mobiusapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.recyclerkit.animations.holder.AnimateChangeViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem
import kotlinx.android.synthetic.main.ticker_item_layout.view.*

class TickerItemDelegate :
    BaseRecyclerViewDelegate<TickerItemDelegate.MarketTicker, TickerItemDelegate.ViewHolder>(R.layout.ticker_item_layout, { pos, data -> data is MarketTicker }) {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, data: MarketTicker, pos: Int, payloads: List<Any>?) {
        payloads?.filterIsInstance<Payload>()?.forEach { holder.applyPayload(it) }

        holder.takeIf { payloads.isNullOrEmpty() }?.applyData(data)
    }

    private fun ViewHolder.applyPayload(payload: Payload) {
        payload.market?.let {
            tickerName.text = it.marketName
        }

        payload.bid?.let { (bid, trend) ->
            tickerBid.text = "Bid: ${String.format("%.${10}f", bid)}"
            when (trend) {
                Trend.UP -> tickerBid.setTextColor(Color.GREEN)
                Trend.DOWN -> tickerBid.setTextColor(Color.RED)
            }
            tickerBid.startAnimation(
                AnimationSet(true).apply {
                    addAnimation(AlphaAnimation(1.0f, 0.5f).apply {
                        duration = 100
                    })
                    addAnimation(AlphaAnimation(.5f, 1.0f).apply {
                        duration = 100
                        startOffset = 100
                    })
                }
            )
        }

        payload.ask?.let { (ask, trend) ->
            tickerAsk.text = "Ask: ${String.format("%.${10}f", ask)}"
            when (trend) {
                Trend.UP -> tickerAsk.setTextColor(Color.GREEN)
                Trend.DOWN -> tickerAsk.setTextColor(Color.RED)
            }
            tickerAsk.startAnimation(
                AnimationSet(true).apply {
                    addAnimation(AlphaAnimation(1.0f, 0.5f).apply {
                        duration = 100
                    })
                    addAnimation(AlphaAnimation(.5f, 1.0f).apply {
                        duration = 100
                        startOffset = 100
                    })
                }
            )
        }
    }

    private fun ViewHolder.applyData(data: MarketTicker) {
        tickerName.text = data.market.marketName
        tickerBid.text = "Bid: ${String.format("%.${10}f", data.ticker.bid)}"
        tickerAsk.text = "Ask: ${String.format("%.${10}f", data.ticker.ask)}"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), AnimateChangeViewHolder {

        override fun canAnimateChange(payloads: List<Any>): Boolean = payloads.filterIsInstance<Payload>().any {
            it.bid != null || it.ask != null
        }

        override fun endChangeAnimation(holder: RecyclerView.ViewHolder) {
            (holder as ViewHolder).apply {
                tickerBid.clearAnimation()
                tickerAsk.clearAnimation()
            }
        }

        val tickerName: TextView = view.tickerName
        val tickerBid: TextView = view.tickerBid
        val tickerAsk: TextView = view.tickerAsk
    }

    private enum class Trend {
        DOWN,
        UP
    }

    private data class Payload(
        val market: Market?,
        val ask: Pair<Double, Trend>?,
        val bid: Pair<Double, Trend>?
    )

    data class MarketTicker(
        val market: Market,
        val ticker: Ticker,
        override val listId: String = market.marketName
    ) : ListItem {

        override fun calculatePayload(oldItem: Any): Any? {
            if (oldItem !is MarketTicker) return null

            return Payload(
                market.takeIf { oldItem.market != market },
                ask = if (oldItem.ticker.ask != ticker.ask)
                    Pair(ticker.ask, if (oldItem.ticker.ask < ticker.ask) Trend.UP else Trend.DOWN)
                else null,
                bid = if (oldItem.ticker.bid != ticker.bid)
                    Pair(ticker.bid, if (oldItem.ticker.bid < ticker.bid) Trend.UP else Trend.DOWN)
                else null
            )
        }
    }

}
