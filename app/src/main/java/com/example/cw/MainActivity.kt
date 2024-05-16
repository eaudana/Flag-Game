package com.example.cw

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.PurpleGrey80

// Demo video link - https://drive.google.com/file/d/1ke8KE3htBvxSDxBpyj9InFuvOcFv5mk7/view?usp=sharing


//This list is used for the lazy column

val countries = listOf(
    "Afghanistan",
    "Albania",
    "Algeria",
    "Andorra",
    "Anguilla",
    "Antarctica",
    "Antigua and Barbuda",
    "Argentina",
    "Armenia",
    "Australia",
    "Austria",
    "Azerbaijan",
    "Bahamas",
    "Bahrain",
    "Bangladesh",
    "Barbados",
    "Belarus",
    "Belgium",
    "Belize",
    "Benin",
    "Bermuda",
    "Bhutan",
    "Bolivia",
    "Bosnia and Herzegovina",
    "Botswana",
    "Brazil",
    "Brunei",
    "Bulgaria",
    "Burkina Faso",
    "Burundi",
    "Cape Verde",
    "Cambodia",
    "Cameroon",
    "Canada",
    "Cayman Islands",
    "Central African Republic",
    "Chad",
    "Chile",
    "China",
    "Christmas Island",
    "Cocos (Keeling) Islands",
    "Colombia",
    "Comoros",
    "Congo",
    "Cook Islands",
    "Costa Rica",
    "Croatia",
    "Cuba",
    "Curaçao",
    "Cyprus",
    "Czech Republic",
    "Denmark",
    "Djibouti",
    "Dominica",
    "Dominican Republic",
    "Ecuador",
    "Egypt",
    "El Salvador",
    "Equatorial Guinea",
    "Eritrea",
    "Estonia",
    "Eswatini",
    "Ethiopia",
    "Europe",
    "Falkland Islands (Malvinas)",
    "Faroe Islands",
    "Fiji",
    "Finland",
    "France",
//    "French Guiana",
    "French Polynesia",
    "French Southern Territories",
    "Gabon",
    "Gambia",
    "Georgia",
    "Germany",
    "Ghana",
    "Gibraltar",
    "Greece",
    "Greenland",
    "Grenada",
    "Guadeloupe",
    "Guam",
    "Guatemala",
    "Guernsey",
    "Guinea",
    "Guinea-Bissau",
    "Guyana",
    "Haiti",
//    "Heard Island and McDonald Islands",
    "Vatican City",
    "Honduras",
    "Hong Kong",
    "Hungary",
    "Iceland",
    "India",
    "Indonesia",
    "Iran",
    "Iraq",
    "Ireland",
    "Isle of Man",
    "Israel",
    "Italy",
    "Jamaica",
    "Japan",
    "Jersey",
    "Jordan",
    "Kazakhstan",
    "Kenya",
    "Kiribati",
    "Korea,North",
    "Korea,South",
    "Kosovo",
    "Kuwait",
    "Kyrgyzstan",
    "Laos",
    "Latvia",
    "Lebanon",
    "Lesotho",
    "Liberia",
    "Libya",
    "Liechtenstein",
    "Lithuania",
    "Luxembourg",
    "Macao",
    "Madagascar",
    "Malawi",
    "Malaysia",
    "Maldives",
    "Mali",
    "Malta",
    "Marshall Islands",
//    "Martinique",
    "Mauritania",
    "Mauritius",
//    "Mayotte",
    "Mexico",
    "Micronesia",
    "Moldova",
    "Monaco",
    "Mongolia",
    "Montenegro",
    "Montserrat",
    "Morocco",
    "Mozambique",
    "Myanmar",
    "Namibia",
    "Nauru",
    "Nepal",
    "Netherlands",
//    "New Caledonia",
    "New Zealand",
    "Nicaragua",
    "Niger",
    "Nigeria",
    "Niue",
    "Norfolk Island",
    "North Macedonia",
    "Northern Mariana Islands",
    "Norway",
    "Oman",
    "Pakistan",
    "Palau",
    "Palestine",
    "Panama",
    "Papua New Guinea",
    "Paraguay",
    "Peru",
    "Philippines",
    "Pitcairn",
    "Poland",
    "Portugal",
    "Puerto Rico",
    "Qatar",
//    "Réunion",
    "Romania",
    "Russia",
    "Rwanda",
//    "Saint Barthélemy",
    "Saint Helena",
    "Saint Kitts and Nevis",
    "Saint Lucia",
//    "Saint Martin",
//    "Saint Pierre and Miquelon",
    "Saint Vincent and the Grenadines",
    "Samoa",
    "San Marino",
    "Sao Tome and Principe",
    "Saudi Arabia",
    "Senegal",
    "Serbia",
    "Seychelles",
    "Sierra Leone",
    "Singapore",
    "Sint Maarten (Dutch part)",
    "Slovakia",
    "Slovenia",
    "Solomon Islands",
    "Somalia",
    "South Africa",
    "South Georgia and the South Sandwich Islands",
    "South Sudan",
    "Spain",
    "Sri Lanka",
    "Sudan",
    "Suriname",
//    "Svalbard and Jan Mayen Islands",
    "Sweden",
    "Switzerland",
    "Syria",
    "Taiwan",
    "Tajikistan",
    "Tanzania",
    "Thailand",
    "Timor-Leste",
    "Togo",
    "Tokelau",
    "Tonga",
    "Trinidad and Tobago",
    "Tunisia",
    "Turkey",
    "Turkmenistan",
    "Turks and Caicos Islands",
    "Tuvalu",
    "Uganda",
    "Ukraine",
    "United Arab Emirates",
    "United Kingdom",
    "United States",
//    "United States Minor Outlying Islands",
    "Uruguay",
    "Uzbekistan",
    "Vanuatu",
    "Venezuela",
    "Vietnam",
    "Virgin Islands, British",
    "Virgin Islands, U.S.",
    "Wallis and Futuna Islands",
    "Western Sahara",
    "Yemen",
    "Zambia",
    "Zimbabwe",
    "England",
    "Northern Ireland",
    "Scotland",
    "Wales"
)


//List mapping the country name to the images in the drawable folder
val countryFlags = listOf(
    "Afghanistan" to R.drawable.af,
    "Albania" to R.drawable.al,
    "Algeria" to R.drawable.dz,
    "Andorra" to R.drawable.ad,
    "Anguilla" to R.drawable.ai,
    "Antarctica" to R.drawable.aq,
    "Antigua and Barbuda" to R.drawable.ag,
    "Argentina" to R.drawable.ar,
    "Armenia" to R.drawable.am,
    "Australia" to R.drawable.au,
    "Austria" to R.drawable.at,
    "Azerbaijan" to R.drawable.az,
    "Bahamas" to R.drawable.bs,
    "Bahrain" to R.drawable.bh,
    "Bangladesh" to R.drawable.bd,
    "Barbados" to R.drawable.bb,
    "Belarus" to R.drawable.by,
    "Belgium" to R.drawable.be,
    "Belize" to R.drawable.bz,
    "Benin" to R.drawable.bj,
    "Bermuda" to R.drawable.bm,
    "Bhutan" to R.drawable.bt,
    "Bolivia" to R.drawable.bo,
    "Bosnia and Herzegovina" to R.drawable.ba,
    "Botswana" to R.drawable.bw,
    "Brazil" to R.drawable.br,
    "Brunei" to R.drawable.bn,
    "Bulgaria" to R.drawable.bg,
    "Burkina Faso" to R.drawable.bf,
    "Burundi" to R.drawable.bi,
    "Cape Verde" to R.drawable.cv,
    "Cambodia" to R.drawable.kh,
    "Cameroon" to R.drawable.cm,
    "Canada" to R.drawable.ca,
    "Cayman Islands" to R.drawable.ky,
    "Central African Republic" to R.drawable.cf,
    "Chad" to R.drawable.td,
    "Chile" to R.drawable.cl,
    "China" to R.drawable.cn,
    "Christmas Island" to R.drawable.cx,
    "Cocos (Keeling) Islands" to R.drawable.cc,
    "Colombia" to R.drawable.co,
    "Comoros" to R.drawable.km,
    "Congo" to R.drawable.cd,
    "Cook Islands" to R.drawable.ck,
    "Costa Rica" to R.drawable.cr,
    "Croatia" to R.drawable.hr,
    "Cuba" to R.drawable.cu,
    "Curaçao" to R.drawable.cw,
    "Cyprus" to R.drawable.cy,
    "Czech Republic" to R.drawable.cz,
    "Denmark" to R.drawable.dk,
    "Djibouti" to R.drawable.dj,
    "Dominica" to R.drawable.dm,
    "Dominican Republic" to R.drawable.doo,
    "Ecuador" to R.drawable.ec,
    "Egypt" to R.drawable.eg,
    "El Salvador" to R.drawable.sv,
    "Equatorial Guinea" to R.drawable.gq,
    "Eritrea" to R.drawable.er,
    "Estonia" to R.drawable.ee,
    "Eswatini" to R.drawable.sz,
    "Ethiopia" to R.drawable.et,
    "Europe" to R.drawable.eu,
    "Falkland Islands (Malvinas)" to R.drawable.fk,
    "Faroe Islands" to R.drawable.fo,
    "Fiji" to R.drawable.fj,
    "Finland" to R.drawable.fi,
    "France" to R.drawable.fr,
//    "French Guiana" to R.drawable.gf,
    "French Polynesia" to R.drawable.pf,
    "French Southern Territories" to R.drawable.tf,
    "Gabon" to R.drawable.ga,
    "Gambia" to R.drawable.gm,
    "Georgia" to R.drawable.ge,
    "Germany" to R.drawable.de,
    "Ghana" to R.drawable.gh,
    "Gibraltar" to R.drawable.gi,
    "Greece" to R.drawable.gr,
    "Greenland" to R.drawable.gl,
    "Grenada" to R.drawable.gd,
    "Guadeloupe" to R.drawable.gp,
    "Guam" to R.drawable.gu,
    "Guatemala" to R.drawable.gt,
    "Guernsey" to R.drawable.gg,
    "Guinea" to R.drawable.gn,
    "Guinea-Bissau" to R.drawable.gw,
    "Guyana" to R.drawable.gy,
    "Haiti" to R.drawable.ht,
//    "Heard Island and McDonald Islands" to R.drawable.hm,
    "Vatican City" to R.drawable.va,
    "Honduras" to R.drawable.hn,
    "Hong Kong" to R.drawable.hk,
    "Hungary" to R.drawable.hu,
    "Iceland" to R.drawable.`is`,
    "India" to R.drawable.`in`,
    "Indonesia" to R.drawable.id,
    "Iran" to R.drawable.ir,
    "Iraq" to R.drawable.iq,
    "Ireland" to R.drawable.ie,
    "Isle of Man" to R.drawable.im,
    "Israel" to R.drawable.il,
    "Italy" to R.drawable.it,
    "Jamaica" to R.drawable.jm,
    "Japan" to R.drawable.jp,
    "Jersey" to R.drawable.je,
    "Jordan" to R.drawable.jo,
    "Kazakhstan" to R.drawable.kz,
    "Kenya" to R.drawable.ke,
    "Kiribati" to R.drawable.ki,
    "Korea,North" to R.drawable.kp,
    "Korea,South" to R.drawable.kr,
    "Kosovo" to R.drawable.xk,
    "Kuwait" to R.drawable.kw,
    "Kyrgyzstan" to R.drawable.kg,
    "Laos" to R.drawable.la,
    "Latvia" to R.drawable.lv,
    "Lebanon" to R.drawable.lb,
    "Lesotho" to R.drawable.ls,
    "Liberia" to R.drawable.lr,
    "Libya" to R.drawable.ly,
    "Liechtenstein" to R.drawable.li,
    "Lithuania" to R.drawable.lt,
    "Luxembourg" to R.drawable.lu,
    "Macao" to R.drawable.mo,
    "Madagascar" to R.drawable.mg,
    "Malawi" to R.drawable.mw,
    "Malaysia" to R.drawable.my,
    "Maldives" to R.drawable.mv,
    "Mali" to R.drawable.ml,
    "Malta" to R.drawable.mt,
    "Marshall Islands" to R.drawable.mh,
//    "Martinique" to R.drawable.mq,
    "Mauritania" to R.drawable.mr,
    "Mauritius" to R.drawable.mu,
//    "Mayotte" to R.drawable.yt,
    "Mexico" to R.drawable.mx,
    "Micronesia" to R.drawable.fm,
    "Moldova" to R.drawable.md,
    "Monaco" to R.drawable.mc,
    "Mongolia" to R.drawable.mn,
    "Montenegro" to R.drawable.me,
    "Montserrat" to R.drawable.ms,
    "Morocco" to R.drawable.ma,
    "Mozambique" to R.drawable.mz,
    "Myanmar" to R.drawable.mm,
    "Namibia" to R.drawable.na,
    "Nauru" to R.drawable.nr,
    "Nepal" to R.drawable.np,
    "Netherlands" to R.drawable.nl,
//    "New Caledonia" to R.drawable.nc,
    "New Zealand" to R.drawable.nz,
    "Nicaragua" to R.drawable.ni,
    "Niger" to R.drawable.ne,
    "Nigeria" to R.drawable.ng,
    "Niue" to R.drawable.nu,
    "Norfolk Island" to R.drawable.nf,
    "North Macedonia" to R.drawable.mk,
    "Northern Mariana Islands" to R.drawable.mp,
    "Norway" to R.drawable.no,
    "Oman" to R.drawable.om,
    "Pakistan" to R.drawable.pk,
    "Palau" to R.drawable.pw,
    "Palestine" to R.drawable.ps,
    "Panama" to R.drawable.pa,
    "Papua New Guinea" to R.drawable.pg,
    "Paraguay" to R.drawable.py,
    "Peru" to R.drawable.pe,
    "Philippines" to R.drawable.ph,
    "Pitcairn" to R.drawable.pn,
    "Poland" to R.drawable.pl,
    "Portugal" to R.drawable.pt,
    "Puerto Rico" to R.drawable.pr,
    "Qatar" to R.drawable.qa,
//    "Réunion" to R.drawable.re,
    "Romania" to R.drawable.ro,
    "Russia" to R.drawable.ru,
    "Rwanda" to R.drawable.rw,
//    "Saint Barthélemy" to R.drawable.bl,
    "Saint Helena" to R.drawable.sh,
    "Saint Kitts and Nevis" to R.drawable.kn,
    "Saint Lucia" to R.drawable.lc,
//    "Saint Martin" to R.drawable.mf,
//    "Saint Pierre and Miquelon" to R.drawable.pm,
    "Saint Vincent and the Grenadines" to R.drawable.vc,
    "Samoa" to R.drawable.ws,
    "San Marino" to R.drawable.sm,
    "Sao Tome and Principe" to R.drawable.st,
    "Saudi Arabia" to R.drawable.sa,
    "Senegal" to R.drawable.sn,
    "Serbia" to R.drawable.rs,
    "Seychelles" to R.drawable.sc,
    "Sierra Leone" to R.drawable.sl,
    "Singapore" to R.drawable.sg,
    "Sint Maarten (Dutch part)" to R.drawable.sx,
    "Slovakia" to R.drawable.sk,
    "Slovenia" to R.drawable.si,
    "Solomon Islands" to R.drawable.sb,
    "Somalia" to R.drawable.so,
    "South Africa" to R.drawable.za,
    "South Georgia and the South Sandwich Islands" to R.drawable.gs,
    "South Sudan" to R.drawable.ss,
    "Spain" to R.drawable.es,
    "Sri Lanka" to R.drawable.lk,
    "Sudan" to R.drawable.sd,
    "Suriname" to R.drawable.sr,
//    "Svalbard and Jan Mayen Islands" to R.drawable.sj,
    "Sweden" to R.drawable.se,
    "Switzerland" to R.drawable.ch,
    "Syria" to R.drawable.sy,
    "Taiwan" to R.drawable.tw,
    "Tajikistan" to R.drawable.tj,
    "Tanzania" to R.drawable.tz,
    "Thailand" to R.drawable.th,
    "Timor-Leste" to R.drawable.tl,
    "Togo" to R.drawable.tg,
    "Tokelau" to R.drawable.tk,
    "Tonga" to R.drawable.to,
    "Trinidad and Tobago" to R.drawable.tt,
    "Tunisia" to R.drawable.tn,
    "Turkey" to R.drawable.tr,
    "Turkmenistan" to R.drawable.tm,
    "Turks and Caicos Islands" to R.drawable.tc,
    "Tuvalu" to R.drawable.tv,
    "Uganda" to R.drawable.ug,
    "Ukraine" to R.drawable.ua,
    "United Arab Emirates" to R.drawable.ae,
    "United Kingdom" to R.drawable.gb,
    "United States" to R.drawable.us,
//    "United States Minor Outlying Islands" to R.drawable.um,
    "Uruguay" to R.drawable.uy,
    "Uzbekistan" to R.drawable.uz,
    "Vanuatu" to R.drawable.vu,
    "Venezuela" to R.drawable.ve,
    "Vietnam" to R.drawable.vn,
    "Virgin Islands, British" to R.drawable.vg,
    "Virgin Islands, U.S." to R.drawable.vi,
    "Wallis and Futuna Islands" to R.drawable.wf,
    "Western Sahara" to R.drawable.eh,
    "Yemen" to R.drawable.ye,
    "Zambia" to R.drawable.zm,
    "Zimbabwe" to R.drawable.zw,
    "England" to R.drawable.eng,
    "Scotland" to R.drawable.sct,
    "Wales" to R.drawable.wls,
    "Northern Ireland" to R.drawable.nir
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myApp()
        }

    }
}


@Preview(showBackground = true)
@Composable
fun myApp() {

    var timerEnabled = remember { mutableStateOf(false) }


    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = PurpleGrey80,
    ) {
        Column(
            modifier = Modifier
                .padding(50.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    var i = Intent(
                        context, GuessTheCountry

                        ::class.java
                    )
                    i.putExtra("timerEnabled", timerEnabled.value)
                    context.startActivity(i)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .width(100.dp)
            ) {
                Text("Guess the Country", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    var i = Intent(
                        context, GuessHints
                        ::class.java
                    )
                    i.putExtra("timerEnabled", timerEnabled.value)
                    context.startActivity(i)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .width(100.dp)
            ) {
                Text("Guess-Hints", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    var i = Intent(
                        context, GuessTheFlag
                        ::class.java
                    )
                    i.putExtra("timerEnabled", timerEnabled.value)
                    context.startActivity(i)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .width(100.dp)
            ) {
                Text("Guess the Flag", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    var i = Intent(
                        context, AdvancedLevel
                        ::class.java
                    )
                    i.putExtra("timerEnabled", timerEnabled.value)
                    context.startActivity(i)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .width(100.dp)
            ) {
                Text("Advanced Level", fontSize = 16.sp)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Switch(
                    checked = timerEnabled.value,
                    onCheckedChange = { timerEnabled.value = it }
                )
                Text(text = "TIMER", fontSize = 16.sp)
            }

        }
    }

}