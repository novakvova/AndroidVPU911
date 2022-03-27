using Atb.Web.Data.Entities.Identity;
using Atb.Web.Models;
using AutoMapper;

namespace Atb.Web.Mapper
{
    public class AppMapProfile : Profile
    {
        public AppMapProfile()
        {
            CreateMap<RegisterViewModel, AppUser>()
                .ForMember(x => x.Photo, opt => opt.Ignore())
                .ForMember(x => x.UserName, opt => opt.MapFrom(x => x.Email));
        }
    }
}
