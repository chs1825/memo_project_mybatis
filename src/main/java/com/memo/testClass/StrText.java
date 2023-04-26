package com.memo.testClass;

import java.util.Arrays;

public class StrText {

    public static void main(String[] args) {

//        String str = "□ 양사는 총 4개 분야에서 협력하기로 했다. ① 안면인식 출입관리시스템 기술검증(PoC) 협력 ② 업무협업 솔루션 지원 및 활용 ③ 교육 신청 및 상담을 위한 AI 챗봇 구축 및 활용 ④ AI 기술 활용 등 서울시 디지털 전환에 대한 공동연구 및 협력이다.\n   ○ 안면인식 출입관리시스템 등 지능형 혁신시스템은 재단에 선제적으로 적용하고, 도입효과를 검증하여 서울시 전역으로 확산할 예정이다.\n  \n□ 특히, 지난 2월에 발표한 “스마트시티 인덱스 리포트 2022”에서 서울시는 도시지능화 부분에서 세계1위를 차지한 바 있으며, 양 기관간 협력을 통해 AI기반 디지털 도시 경쟁력을 더욱 강화할 수 있을 것으로 기대된다. ";
        String str = "□ 양사는 총 4개 분야에서 협력하기로 했다.";


//        if(str.contains("\n\\s*\n")){
////            System.out.println("trur");
//        }
        System.out.println("str.contains(\"\\n\\\\s*\\n\") = " + str.contains("\n\\s*\n"));
        System.out.println(" = " + str.contains("\n\\s*"));
        System.out.println(Arrays.toString(str.split("\n\\s*\n")));





    }





}
