using FluentValidation;

namespace nocturnal.Filters;

public class ValidationFilter<T> : IEndpointFilter
{
    public async ValueTask<object?> InvokeAsync(EndpointFilterInvocationContext context, EndpointFilterDelegate next)
    {
        var validator = context.HttpContext.RequestServices.GetService<IValidator<T>>();

        if (validator is null)
            return await next(context);

        var entity = context.Arguments
            .OfType<T>()
            .FirstOrDefault(t => t?.GetType() == typeof(T));

        if (entity is null)
            return Results.Problem("Could not find entity to validate.");

        var validation = await validator.ValidateAsync(entity);

        if (!validation.IsValid)
            return Results.ValidationProblem(validation.ToDictionary());

        return await next(context);
    }
}