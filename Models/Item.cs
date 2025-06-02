using System.ComponentModel.DataAnnotations;

namespace nocturnal.Models;

public class Item : Base
{
    [Required]
    [StringLength(50)]
    public required string Key { get; set; }
    [Required]
    [StringLength(255)]
    public required string Name { get; set; }
    [StringLength(1000)]
    public string? Description { get; set; }
    
    [Required]
    public required Project Project { get; set; }
}