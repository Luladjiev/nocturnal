using Microsoft.OpenApi.Models;
using Scalar.AspNetCore;

namespace nocturnal.Extensions;

/// <summary>
///     Provides extension methods to configure OpenAPI documentation and UI for the application.
/// </summary>
public static class OpenApiExtensions
{
    private const string OpenApiRoute = "/api/docs/openapi.json";

    public static void AddNocturnalOpenApi(this IServiceCollection services)
    {
        services.AddOpenApi(options =>
        {
            options.AddDocumentTransformer((document, _, _) =>
            {
                document.Info = new OpenApiInfo
                {
                    Title = "Nocturnal API",
                    Description = "Nocturnal API Reference",
                    Contact = new OpenApiContact
                    {
                        Name = "Peter Luladjiev",
                        Email = "nocturnal@luladjiev.com",
                        Url = new Uri("https://github.com/luladjiev/nocturnal")
                    }
                };

                return Task.CompletedTask;
            });
        });
    }

    public static void UseNocturnalOpenApiUi(this WebApplication app)
    {
        app.MapOpenApi(OpenApiRoute);
        app.MapScalarApiReference("/api/docs",
            options => options.WithTitle("Nocturnal API Reference").WithOpenApiRoutePattern(OpenApiRoute));
    }
}