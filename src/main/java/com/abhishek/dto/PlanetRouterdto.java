package com.abhishek.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlanetRouterdto {

	private String planetOrder;
	private String name;
	private String description;
	private BasicDetails basicDetails;
	private String source;
	private String wikiLink;
	private IMGSRC imgSrc;
	private String id;
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class BasicDetails{
	private String volume;
	private String mass;
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class IMGSRC{
	private String img;
	private String imgDescription;
}
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//public class PlanetRouterdto {
//    private int planetOrder;
//    private String name;
//    private String description;
//    private BasicDetails basicDetails;
//    private String source;
//    private String wikiLink;
//    private ImgSrc imgSrc;
//    private int id;
//
//    // Getters and setters
//	  @NoArgsConstructor
//	  @AllArgsConstructor
//	  @Getter
//	  @Setter
//	  @ToString
//    public static class BasicDetails {
//        private String volume;
//        private String mass;
//
//        // Getters and setters
//    }
//
//	  @ToString
//	@NoArgsConstructor
//	@AllArgsConstructor
//	@Getter
//	@Setter
//    public static class ImgSrc {
//        private String img;
//        private String imgDescription;
//
//        // Getters and setters
//    }
//}
//

/*
 * {
  "planetOrder": 1,
  "name": "Mercury",
  "description": "Mercury is the smallest planet in the Solar System and the closest to the Sun. Its orbit around the Sun takes 87.97 Earth days, the shortest of all the Sun's planets.",
  "basicDetails": {
    "volume": "6.083 x 10^10 km^3",
    "mass": "3.3011 x 10^23 kg"
  },
  "source": "Wikipedia",
  "wikiLink": "https://en.wikipedia.org/wiki/Mercury_(planet)",
  "imgSrc": {
    "img": "https://upload.wikimedia.org/wikipedia/commons/4/4a/Mercury_in_true_color.jpg",
    "imgDescription": "Mercury in true color (by MESSENGER in 2008)"
  },
  "id": 1
}
 *
 */