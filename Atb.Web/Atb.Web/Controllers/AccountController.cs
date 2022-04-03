using Atb.Web.Data.Entities.Identity;
using Atb.Web.Models;
using Atb.Web.Services;
using AutoMapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

namespace Atb.Web.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AccountController : ControllerBase
    {
        private readonly IMapper _mapper;
        private readonly IJwtTokenService _jwtTokenService;
        private readonly UserManager<AppUser> _userManager;
        public AccountController(UserManager<AppUser> userManager,
            IJwtTokenService jwtTokenService, IMapper mapper)
        {
            _userManager = userManager;
            _mapper = mapper;
            _jwtTokenService = jwtTokenService;
        }

        [HttpPost]
        [Route("register")]
        public async Task<IActionResult> Register([FromBody] RegisterViewModel model)
        {
            var user = _mapper.Map<AppUser>(model);
            var result = await _userManager.CreateAsync(user, model.Password);

            if (!result.Succeeded)
                return BadRequest(new { errors = result.Errors });


            return Ok(new { token = _jwtTokenService.CreateToken(user) });
        }
    }
}
