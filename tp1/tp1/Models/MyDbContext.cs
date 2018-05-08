using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;

namespace tp1.Models
{
    public class MyDbContext : DbContext
    {
        public MyDbContext()
            :base("DefaultMyDbContext")
    {}
        public DbSet<Post> Posts { get; set; }
        public DbSet<User> Users { get; set; }
    }
}