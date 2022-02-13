package ru.gozerov.domain.usecase

import ru.gozerov.domain.entity.*
import ru.gozerov.domain.usecase.Adapters.HIT_OF_SALE_CARD_ADAPTER
import ru.gozerov.domain.usecase.Adapters.MAG_CARDS_ADAPTER
import ru.gozerov.domain.usecase.Adapters.MAIN_VIEW_PAGER_ADAPTER
import ru.gozerov.domain.usecase.Adapters.PRODUCT_OF_THE_DAY_CARD_ADAPTER
import ru.gozerov.domain.usecase.Adapters.SALE_CARD_ADAPTER
import ru.gozerov.domain.usecase.Adapters.SALE_CARD_ADAPTER_BOTTOM
import ru.gozerov.domain.usecase.Adapters.TOP_CARD_ADAPTER
import ru.gozerov.domain.usecase.Adapters.WATCHED_PRODUCTS_CARD_ADAPTER
import ru.gozerov.domain.usecase.TopCards.ABOUT_ORDERS
import ru.gozerov.domain.usecase.TopCards.DISCOUNTS
import ru.gozerov.domain.usecase.TopCards.LOGIN
import ru.gozerov.domain.usecase.TopCards.PERSONALS
import ru.gozerov.domain.usecase.TopCards.TAKE_ORDERS_FROM_SHOP

class GetAdaptersDataUseCase(
    private val strings: Map<String, String>,
    private val icons: Map<String, Int>
) {

    fun execute(): MutableMap<String, List<Any>> {
        return mutableMapOf(
            TOP_CARD_ADAPTER to generateTopCards(),
            SALE_CARD_ADAPTER to generateSaleCards(),
            PRODUCT_OF_THE_DAY_CARD_ADAPTER to generateProductsOfTheDay(),
            HIT_OF_SALE_CARD_ADAPTER to generateProductsOfTheDay(),
            SALE_CARD_ADAPTER_BOTTOM to generateSaleCards(),
            MAIN_VIEW_PAGER_ADAPTER to generateSimpleProductListWithTabs(),
            MAG_CARDS_ADAPTER to generateMags(),
            WATCHED_PRODUCTS_CARD_ADAPTER to generateSimpleProductList()
        )
    }

    //TODO(Перенести всю логику в дату, прокинуть репозиторий в зависимости)

    private fun generateTopCards(): List<TopCard> {
        return listOf(
            TopCard(strings.getValue(LOGIN), icons.getValue(LOGIN)),
            TopCard(strings.getValue(ABOUT_ORDERS), icons.getValue(ABOUT_ORDERS)),
            TopCard(strings.getValue(TAKE_ORDERS_FROM_SHOP), icons.getValue(TAKE_ORDERS_FROM_SHOP)),
            TopCard(strings.getValue(PERSONALS), icons.getValue(PERSONALS)),
            TopCard(strings.getValue(DISCOUNTS), icons.getValue(DISCOUNTS)),
        )
    }

    private fun generateSaleCards(): List<SaleCard> {
        return (1..4).map {
            val imageUrl = if (it==1 || it==3)
                "https://logistics.ru/sites/default/files/2019-01/header-des.png"
            else
                "https://www.prophotoshow.net/blog/wp-content/uploads/2010/03/Doorway-to-Winter-600x400.jpg"
            SaleCard(
                id = it.toLong(),
                backgroundImageUrl = imageUrl
            )
        }
    }

    private fun generateProductsOfTheDay(): List<Product> {
        return (1..3).map {
            Product(
                it.toLong(),
                productName = "Смартфон HUAWEI nova 8 Blush Gold (ANG-LX1)",
                productImage = "https://img.mvideo.ru/Pdb/small_pic/200/30058491b.jpg",
                rating = 4.7,
                reviews = 40,
                oldPrice = "39 990 ₽",
                newPrice = "19990"
            )
        }
    }


    private fun generateSimpleProductListWithTabs(): List<Map<String, List<ProductSimple>>> {
        val products = (0 until 3).map {
            ProductSimple(
                id = it.toLong(),
                productName = "Смартфон HUAWEI nova 8 Blush Gold (ANG-LX1)",
                price = "30 990 ₽",
                image = "https://img.mvideo.ru/Pdb/small_pic/200/30058491b.jpg"
            )
        }
        return listOf(mapOf("Новинки" to products, "В тренде" to products, "Apple" to products))
    }

    private fun generateSimpleProductList(): List<ProductSimple> {
        return (0 until 8).map {
            ProductSimple(
                id = it.toLong(),
                productName = "Смартфон HUAWEI nova 8 Blush Gold (ANG-LX1)",
                price = "30 990 ₽",
                image = "https://img.mvideo.ru/Pdb/small_pic/200/30058491b.jpg"
            )
        }
    }

    private fun generateMags(): List<MagContent> {
        return (1..5).map {
            MagContent(
                id = it.toLong(),
                magType = "Обзор",
                shortDescription = "Гаджеты и приложения, которые используют спортсмены",
                magBackground = "https://static.mvideo.ru/media/Promotions/Promo_Page/2022/February/obzor-gadzhety-i-prilozheniya-kotorye-ispolzuyut-sportsmeny/obzor-gadzhety-i-prilozheniya-kotorye-ispolzuyut-sportsmeny-top1-d.png"
            )
        }
    }

}
object Adapters {
    const val TOP_CARD_ADAPTER = "TOP_CARD_ADAPTER"
    const val SALE_CARD_ADAPTER = "SALE_CARD_ADAPTER"
    const val PRODUCT_OF_THE_DAY_CARD_ADAPTER = "PRODUCT_OF_THE_DAY_CARD_ADAPTER"
    const val HIT_OF_SALE_CARD_ADAPTER = "HIT_OF_SALE_CARD_ADAPTER"
    const val SALE_CARD_ADAPTER_BOTTOM = "SALE_CARD_ADAPTER_BOTTOM"
    const val MAIN_VIEW_PAGER_ADAPTER = "MAIN_VIEW_PAGER_ADAPTER"
    const val MAG_CARDS_ADAPTER = "MAG_CARDS_ADAPTER"
    const val WATCHED_PRODUCTS_CARD_ADAPTER = "WATCHED_PRODUCTS_CARD_ADAPTER"
}

object Icons {
    const val ICON_CIRCLE_ARROW = "ICON_CIRCLE_ARROW"
}

object TopCards {
    const val LOGIN = "LOGIN"
    const val ABOUT_ORDERS = "ABOUT_ORDERS"
    const val TAKE_ORDERS_FROM_SHOP = "TAKE_ORDERS_FROM_SHOP"
    const val PERSONALS = "PERSONALS"
    const val DISCOUNTS = "DISCOUNTS"
}