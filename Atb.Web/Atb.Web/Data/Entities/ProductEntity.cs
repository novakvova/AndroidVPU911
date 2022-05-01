using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Atb.Web.Data.Entities
{
    [Table("tblProducts")]
    public class ProductEntity
    {
        [Key]
        public int Id { get; set; }
        [Required, StringLength(255)]
        public string Name { get; set; }
        public decimal Price { get; set; }
        public DateTime DateCreate { get; set; }
    }
}
