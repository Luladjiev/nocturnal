import { APP_INITIALIZER, ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { APP_ROUTES } from './app.routes';
import { HttpClient, provideHttpClient } from '@angular/common/http';
import { catchError, Observable } from 'rxjs';
import { Location } from '@angular/common';

function initializeAppFactory(
  httpClient: HttpClient,
  location: Location
): () => Observable<unknown> {
  return () =>
    httpClient.head('/api/user').pipe(
      catchError(() => {
        location.go('/login');
        return [];
      })
    );
}

export const AppConfig: ApplicationConfig = {
  providers: [
    provideRouter(APP_ROUTES),
    provideHttpClient(),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeAppFactory,
      deps: [HttpClient, Location],
      multi: true,
    },
  ],
};
