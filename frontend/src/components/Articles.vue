<template>
  <v-flex>
    <v-text-field label="내용으로까지 검색하려면 엔터치세요!" v-model="search">
    </v-text-field>
    <v-list two-line v-for="(listItem, index) in calData" :key="index">
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="text--primary">
            {{ listItem.subject }}
          </v-list-item-title>
          <v-list-item-subtitle>
            {{ listItem.desc }}
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>
    </v-list>
    <br />
    <v-pagination v-model="curPageNum" :length="numOfPages"> </v-pagination>
  </v-flex>
</template>

<script>
export default {
  name: "Articles",

  data: () => ({
    search: "",
    listData: [],
    searchData: [],
    dataPerPage: 8,
    curPageNum: 1,
    curSelectIndex: 0,

    newData: {
      id: Number,
      subject: "",
      desc: ""
    },
    selectedData: {
      id: Number,
      subject: "",
      desc: ""
    }
  }),
  created() {
    this.listData = [
      { id: 1, subject: "Malawi", desc: "Wels" },
      { id: 2, subject: "Cuba", desc: "Sale" },
      { id: 3, subject: "Somalia", desc: "Lahore" },
      {
        id: 4,
        subject: "Togo",
        desc: "Termeno sulla strada del vino/Tramin an der Weinstrasse"
      },
      { id: 5, subject: "Slovenia", desc: "Edam" },
      { id: 6, subject: "Tonga", desc: "Vinci" },
      { id: 7, subject: "Kenya", desc: "Te Awamutu" },
      { id: 8, subject: "Japan", desc: "Ferrazzano" },
      { id: 9, subject: "Finland", desc: "Nil-Saint-Vincent-Saint-Martin" },
      { id: 10, subject: "Dominica", desc: "Paglieta" },
      { id: 11, subject: "Solomon Islands", desc: "Vilvoorde" },
      { id: 12, subject: "Monaco", desc: "Tonk" },
      { id: 13, subject: "Cook Islands", desc: "Sint-Pieters-Kapelle" },
      { id: 14, subject: "Gibraltar", desc: "Develi" },
      { id: 15, subject: "Mauritania", desc: "Etobicoke" },
      { id: 16, subject: "Sierra Leone", desc: "Norman Wells" },
      { id: 17, subject: "Ghana", desc: "Charleroi" },
      { id: 18, subject: "Saint Martin", desc: "Düsseldorf" },
      { id: 19, subject: "Uganda", desc: "Dhuy" },
      { id: 20, subject: "Serbia", desc: "Rampur" },
      { id: 21, subject: "Bangladesh", desc: "Kempten" },
      { id: 22, subject: "Bosnia and Herzegovina", desc: "Okigwe" },
      { id: 23, subject: "Somalia", desc: "Valpelline" },
      { id: 24, subject: "Libya", desc: "Beverlo" },
      { id: 25, subject: "Israel", desc: "Coalhurst" },
      { id: 26, subject: "Greece", desc: "Castel Ritaldi" },
      { id: 27, subject: "United Kingdom (Great Britain)", desc: "Benestare" },
      { id: 28, subject: "Singapore", desc: "Tsiigehtchic" },
      { id: 29, subject: "Saint Pierre and Miquelon", desc: "Jhang" },
      {
        id: 30,
        subject: "South Georgia and The South Sandwich Islands",
        desc: "Mobile"
      },
      { id: 31, subject: "Yemen", desc: "Koningshooikt" },
      { id: 32, subject: "Albania", desc: "Stirling" },
      { id: 33, subject: "Laos", desc: "Beypazarı" },
      { id: 34, subject: "Bouvet Island", desc: "Florida" },
      { id: 35, subject: "Macao", desc: "Calmar" },
      { id: 36, subject: "Austria", desc: "Invergordon" },
      { id: 37, subject: "Palau", desc: "Balclutha" },
      { id: 38, subject: "Denmark", desc: "Bozeman" },
      { id: 39, subject: "Sierra Leone", desc: "Assiniboia" },
      { id: 40, subject: "Austria", desc: "Champigny-sur-Marne" },
      { id: 41, subject: "Finland", desc: "Calera de Tango" },
      { id: 42, subject: "Lesotho", desc: "Ancarano" },
      { id: 43, subject: "Western Sahara", desc: "Cochamó" },
      { id: 44, subject: "Guernsey", desc: "Villers-lez-Heest" },
      { id: 45, subject: "Turkey", desc: "Liedekerke" },
      { id: 46, subject: "Dominica", desc: "Nanterre" },
      {
        id: 47,
        subject: "Saint Helena, Ascension and Tristan da Cunha",
        desc: "Crato"
      },
      { id: 48, subject: "Czech Republic", desc: "Lossiemouth" },
      { id: 49, subject: "Croatia", desc: "Sale" },
      {
        id: 50,
        subject: "South Georgia and The South Sandwich Islands",
        desc: "North Shore"
      },
      { id: 51, subject: "Malawi", desc: "Newtonmore" },
      { id: 52, subject: "Marshall Islands", desc: "Piracicaba" },
      { id: 53, subject: "Nepal", desc: "Ponoka" },
      { id: 54, subject: "Gibraltar", desc: "Rabbi" },
      { id: 55, subject: "Åland Islands", desc: "Biarritz" },
      { id: 56, subject: "Nepal", desc: "Sant'Elia a Pianisi" },
      { id: 57, subject: "Virgin Islands, British", desc: "Welland" },
      { id: 58, subject: "Nauru", desc: "Sesto al Reghena" },
      { id: 59, subject: "Antarctica", desc: "Eluru" },
      { id: 60, subject: "Seychelles", desc: "San Francisco" },
      { id: 61, subject: "Thailand", desc: "Mango" },
      { id: 62, subject: "Gambia", desc: "Santa Cruz" },
      {
        id: 63,
        subject: "Svalbard and Jan Mayen Islands",
        desc: "Sant'Angelo a Cupolo"
      },
      { id: 64, subject: "Honduras", desc: "Villata" },
      { id: 65, subject: "Mexico", desc: "Sadiqabad" },
      {
        id: 66,
        subject: "Holy See (Vatican City State)",
        desc: "Villenave-d'Ornon"
      },
      { id: 67, subject: "Colombia", desc: "Charlottetown" },
      { id: 68, subject: "South Africa", desc: "Latronico" },
      { id: 69, subject: "Korea, South", desc: "Alajuela" },
      { id: 70, subject: "Cambodia", desc: "Montbéliard" },
      { id: 71, subject: "Palau", desc: "Purmerend" },
      { id: 72, subject: "Uzbekistan", desc: "Norrköping" },
      { id: 73, subject: "Rwanda", desc: "Serramonacesca" },
      { id: 74, subject: "Cayman Islands", desc: "Court-Saint-Etienne" },
      { id: 75, subject: "Algeria", desc: "Gonnosfanadiga" },
      { id: 76, subject: "Samoa", desc: "Banff" },
      { id: 77, subject: "Libya", desc: "Amberloup" },
      { id: 78, subject: "Mayotte", desc: "Merchtem" },
      { id: 79, subject: "Bahamas", desc: "Tuscaloosa" },
      { id: 80, subject: "Tunisia", desc: "Fogo" },
      { id: 81, subject: "Liberia", desc: "Launceston" },
      { id: 82, subject: "Guam", desc: "Glendale" },
      { id: 83, subject: "Georgia", desc: "Port Blair" },
      { id: 84, subject: "Isle of Man", desc: "Trochu" },
      { id: 85, subject: "Cook Islands", desc: "Lawton" },
      { id: 86, subject: "Israel", desc: "Istres" },
      { id: 87, subject: "Guam", desc: "Portland" },
      { id: 88, subject: "Brazil", desc: "Barrhead" },
      { id: 89, subject: "Japan", desc: "Walhain" },
      { id: 90, subject: "Bolivia", desc: "Motherwell" },
      { id: 91, subject: "French Polynesia", desc: "Stroud" },
      { id: 92, subject: "Portugal", desc: "Airdrie" },
      { id: 93, subject: "Guinea-Bissau", desc: "Multan" },
      {
        id: 94,
        subject: "Bosnia and Herzegovina",
        desc: "Challand-Saint-Victor"
      },
      { id: 95, subject: "Reunion", desc: "Moncton" },
      { id: 96, subject: "Bhutan", desc: "Merthyr Tydfil" },
      { id: 97, subject: "Greece", desc: "Rockford" },
      { id: 98, subject: "Swaziland", desc: "San Nicolás" },
      { id: 99, subject: "Antigua and Barbuda", desc: "Rio Grande" },
      { id: 100, subject: "Portugal", desc: "Aalbeke" }
    ];
  },
  computed: {
    startOffset() {
      return (this.curPageNum - 1) * this.dataPerPage;
    },
    endOffset() {
      return this.startOffset + this.dataPerPage;
    },
    numOfPages() {
      return Math.ceil(this.searchData.length / this.dataPerPage);
    },
    calData() {
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      this.searchData = this.listData
        .filter(data => {
          return data.subject.toLowerCase().includes(this.search.toLowerCase());
        })
        .slice(0);

      return this.searchData.slice(this.startOffset, this.endOffset);
    }
  },
  methods: {}
};
</script>

<style></style>
