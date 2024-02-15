package org.unizd.rma.subasic.spacecraft
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import org.unizd.rma.subasic.spacecraft.data.datasources.room.RoomSpaceCraftDataSource
import org.unizd.rma.subasic.spacecraft.data.interfaces.SpaceCraftDataSource
import org.unizd.rma.subasic.spacecraft.data.interfaces.SpaceCraftDatabase
import org.unizd.rma.subasic.spacecraft.domain.interfaces.SpaceCraftRepository
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.CreateSpaceCraftUseCase
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.DeleteSpaceCraftUseCase
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.GetAllSpaceCraftsUseCase
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.GetSpaceCraftUseCase
import org.unizd.rma.subasic.spacecraft.domain.interfaces.usecases.UpdateSpaceCraftUseCase
import org.unizd.rma.subasic.spacecraft.domain.repositories.SpaceCraftRepositoryImpl
import org.unizd.rma.subasic.spacecraft.domain.usecases.spacecraft.CreateSpaceCraftUseCaseImpl
import org.unizd.rma.subasic.spacecraft.domain.usecases.spacecraft.DeleteSpaceCraftUseCaseImpl
import org.unizd.rma.subasic.spacecraft.domain.usecases.spacecraft.GetAllSpaceCraftsUseCaseImpl
import org.unizd.rma.subasic.spacecraft.domain.usecases.spacecraft.GetSpaceCraftUseCaseImpl
import org.unizd.rma.subasic.spacecraft.domain.usecases.spacecraft.UpdateSpaceCraftUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object AppModule {

    @Provides
    @Singleton
    fun providesExpenseDataSource(
        @ApplicationContext
        context: Context
    ): SpaceCraftDataSource {
        return RoomSpaceCraftDataSource(
            dao = Room.databaseBuilder(
                context,
                SpaceCraftDatabase::class.java,
                SpaceCraftDatabase.DATABASE_NAME
            ).build().spaceCraftDao
        )
    }

    @Provides
    @Singleton
    fun providesBookRepository(
        dataSource: SpaceCraftDataSource
    ): SpaceCraftRepository {
        return SpaceCraftRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetBookUseCase(
        repository: SpaceCraftRepository
    ): GetAllSpaceCraftsUseCase {
        return GetAllSpaceCraftsUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesDeleteBookUseCase(repository: SpaceCraftRepository): DeleteSpaceCraftUseCase {
        return DeleteSpaceCraftUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetBookUseCase(repository: SpaceCraftRepository): GetSpaceCraftUseCase {
        return GetSpaceCraftUseCaseImpl(repository)

    }

    @Provides
    @Singleton
    fun providesUpdateBookUseCase(repository: SpaceCraftRepository): UpdateSpaceCraftUseCase{
        return UpdateSpaceCraftUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesCreateBookUseCase(
        repository: SpaceCraftRepository
    ): CreateSpaceCraftUseCase {
        return CreateSpaceCraftUseCaseImpl(repository)
    }
}