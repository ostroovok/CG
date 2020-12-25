using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CG_5
{
    public interface IAbstractPlanet
    {

        void Update(double dt);
        void ObjLocation();
        PlanetParameters ObjParameters();
    }
}
