package ru.gozerov.domain.usecase

import ru.gozerov.domain.entity.BonusProgram

class GetBonusProgramsUseCase  {

    fun execute(iconId: Int): List<BonusProgram> {
        return (0 until 3).map {
            BonusProgram(
                title = "Кэшбек 3% за любые покупки",
                description = "и до 25% - при оплате онлайн",
                icon = iconId
            )
        }
    }

}