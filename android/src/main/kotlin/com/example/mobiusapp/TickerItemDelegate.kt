package com.example.mobiusapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiusapp.databinding.TickerItemLayoutBinding
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.recyclerkit.animations.holder.AnimateChangeViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.BaseRecyclerViewHolder
import com.revolut.recyclerkit.delegates.ListItem

class TickerItemDelegate :
    BaseRecyclerViewDelegate<TickerItemDelegate.MarketTicker, TickerItemDelegate.ViewHolder>(
        R.layout.ticker_item_layout,
        { pos, data -> data is MarketTicker }) {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun onBindViewHolder(
        holder: ViewHolder,
        data: MarketTicker,
        pos: Int,
        payloads: List<Any>?
    ) {
        super.onBindViewHolder(holder, data, pos, payloads)
        payloads?.filterIsInstance<Payload>()?.forEach { holder.applyPayload(it) }

        holder.takeIf { payloads.isNullOrEmpty() }?.applyData(data)
    }

    @SuppressLint("SetTextI18n")
    private fun ViewHolder.applyPayload(payload: Payload) {
        payload.market?.let {
            tickerName.text = it.marketName
        }

        payload.bid?.let { (bid, trend) ->
            tickerBid.text = "Bid: ${String.format("%.${10}f", bid)}"
            tickerBid.animateTrend(trend)
        }

        payload.ask?.let { (ask, trend) ->
            tickerAsk.text = "Ask: ${String.format("%.${10}f", ask)}"
            tickerAsk.animateTrend(trend)
        }
    }

    private fun TextView.animateTrend(trend: Trend) {
        clearAnimation()
        when (trend) {
            Trend.UP -> this.setTextColor(Color.GREEN)
            Trend.DOWN -> this.setTextColor(Color.RED)
        }
        startAnimation(
            AnimationSet(true).apply {
                addAnimation(AlphaAnimation(1.0f, 0.5f).apply {
                    duration = ANIMATION_DURATION / 2
                })
                addAnimation(AlphaAnimation(.5f, 1.0f).apply {
                    duration = ANIMATION_DURATION / 2
                    startOffset = ANIMATION_DURATION / 2
                })
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(p0: Animation?) = Unit

                    override fun onAnimationEnd(p0: Animation?) {
                        this@animateTrend.setTextColor(
                            ContextCompat.getColor(
                                context,
                                android.R.color.darker_gray
                            )
                        )
                    }

                    override fun onAnimationStart(p0: Animation?) = Unit

                })
            }
        )
    }

    private fun ViewHolder.applyData(data: MarketTicker) {
        tickerName.text = data.market.marketName
        tickerBid.text = "Bid: ${String.format("%.${10}f", data.ticker.bid)}"
        tickerAsk.text = "Ask: ${String.format("%.${10}f", data.ticker.ask)}"
    }

    class ViewHolder(view: View) : BaseRecyclerViewHolder(view), AnimateChangeViewHolder {
        val binding = TickerItemLayoutBinding.bind(view)

        override fun canAnimateChange(payloads: List<Any>): Boolean =
            payloads.filterIsInstance<Payload>().any {
                it.bid != null || it.ask != null
            }

        override fun endChangeAnimation(holder: RecyclerView.ViewHolder) {
            (holder as ViewHolder).apply {
                tickerBid.clearAnimation()
                tickerAsk.clearAnimation()
            }
        }

        val tickerName: TextView = binding.tickerName
        val tickerBid: TextView = binding.tickerBid
        val tickerAsk: TextView = binding.tickerAsk
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

    companion object {
        const val ANIMATION_DURATION = 300L
    }

}
