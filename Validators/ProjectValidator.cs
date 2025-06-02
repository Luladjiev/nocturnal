using FluentValidation;
using nocturnal.DTO;

namespace nocturnal.Validators;

public class ProjectValidator : AbstractValidator<CreateProjectDto>
{
    public ProjectValidator()
    {
        RuleFor(p => p.Key).NotEmpty().MaximumLength(5);
        RuleFor(p => p.Name).NotEmpty().MaximumLength(100);
        RuleFor(p => p.Description).MaximumLength(500);
    }
}