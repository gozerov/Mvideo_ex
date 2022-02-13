package ru.gozerov.domain.usecase

import ru.gozerov.domain.entity.BonusProgram
import ru.gozerov.domain.usecase.Icons.ICON_CIRCLE_ARROW

class GetBonusProgramsUseCase(private val icons: Map<String, Int>) {

    fun execute(): List<BonusProgram> {
        return (0 until 3).map {
            BonusProgram(
                title = "Кэшбек 3% за любые покупки",
                description = "и до 25% - при оплате онлайн",
                icon = icons.getValue(ICON_CIRCLE_ARROW)
            )
        }
    }

}