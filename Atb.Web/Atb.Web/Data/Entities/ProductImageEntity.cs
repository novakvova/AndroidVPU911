using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Atb.Web.Data.Entities
{
    [Table("btlProductImages")]
    public class ProductImageEntity
    {
        public int Id { get; set; }
        [Required, StringLength(255)]
        public string Name { get; set; }
        [ForeignKey("Product")]
        public int ProductId { get; set; }
        virtual public ProductEntity Product { get; set; }
    }
}
