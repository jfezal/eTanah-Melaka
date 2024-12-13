<%-- 
    Document   : draf_pertimbangan_jktd2
    Created on : Oct 27, 2010, 8:51:04 PM
    Author     : nurul.izza
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>
<script type="text/javascript">


    $(document).ready(function() {
     
    <%--$('#suku').hide();
    $('#ta').hide();--%>
    <%--alert("enter");--%>
            var v = '${actionBean.fasaPermohonan.keputusan.kod}';
    <%--alert(v);--%>
            if(v =="T"){
                $('#suku').hide();
                $('#ta').show();
            }else if(v == "L"){
                $('#suku').show();
                $('#ta').hide();
            }else{
                $('#suku').hide();
                $('#ta').hide();
            }

        });
        function changeHideSuku(value){
    <%-- alert(value);--%>
            if(value == "T"){
                $('#suku').hide();
                $('#ta').show();
            }else if(value == "L"){
                $('#suku').show();
                $('#ta').hide();
            }else{
                $('#suku').hide();
                $('#ta').hide();
            }
        }
    <%--           function addRow(tableID) {

               var table = document.getElementById(tableID);
               var rowCount = table.rows.length;
               var row = table.insertRow(rowCount);

               var cell0 = row.insertCell(0);
               cell0.innerHTML = "<b> 4." +(rowCount+1)+"</b>";

               var cell1 = row.insertCell(1);
               var element1 = document.createElement("textarea");
               element1.t = "kandungan"+(rowCount+1);
               element1.rows = 5;
               element1.cols = 150;
               element1.name ="kandungan"+(rowCount+1);
               element1.id ="kandungan"+(rowCount+1);
               cell1.appendChild(element1);

               document.getElementById("rowCount").value=rowCount+1;
           }--%>

    <%--       function addRow(tableid) {

        // alert(tableID);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 4." +(rowCount+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan"+tableNo+""+(rowCount+1);
        element1.rows = 7;
        element1.cols = 160;
        element1.name ="kandungan"+tableNo+""+(rowCount+1);
        element1.id ="kandungan"+tableNo+""+(rowCount+1);
        cell1.appendChild(element1);

        document.getElementById("rowCount"+tableNo).value=rowCount+1;
    }--%>function menyimpan(index,i,f)
        {
            /*
             * LEGEND : 4 FOR PTD
             */
            var kand;
            if(index == 2)
                kand = document.getElementById("kandungan2"+i).value;
            if(index == 3)
                kand = document.getElementById("kandungan3"+i).value;
        
            if(index==4){
                kand = document.getElementById("kandungan4"+i).value;
            }
            if(index==5){
                kand = document.getElementById("kandungan5"+i).value;
            }
            if(index==6){
                kand = document.getElementById("kandungan6"+i).value;
            }
            if(index==7){
                kand = document.getElementById("kandungan7"+i).value;
            }
            if(index==8){
                kand = document.getElementById("kandungan8"+i).value;
            }
            if(index==9){
                kand = document.getElementById("kandungan9"+i).value;
            }
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_pertimbangan_jktd?simpanKandungan&index='+index+'&kandungan='+kand,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
  
       
       
        }
    
        function addRow(index,f)
        {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_pertimbangan_jktd?tambahRow&index='+index,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
        function deleteRow(idKandungan,f)
        {
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/draf_pertimbangan_jktd?deleteRow&idKandungan='+idKandungan,q,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
            }
        }
        function cariPopup(){
            // alert("search:"+index);
            var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?search';
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
        }

        function cariPopup2(){
            // alert("search:"+index);
            var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?showFormCariKodSekatan';
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
        }

</script>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafPertimbanganJktdActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.idKertas"/>
    <s:hidden name="edit" id="edit"/>
    <s:hidden name="editPTD" id="editPTD"/>
    <s:hidden name="editPTG" id="editPTG"/>
    <s:hidden name="openPTD" id="openPTD"/>
    <s:hidden name="openPTG" id="openPTG"/>
    <s:hidden name="firstTimeOpen" id="firstTimeOpen"/>
    <table width="90%" border="0" >
        <!--        <tr>
                    <td colspan="4">
                        <div class="content" align="right">
                            KM No: <s:hidden name="kmno" id="kmno"/>
                        </div>
                    </td>
                </tr>-->
        <tr>
            <td colspan="4">
                <div class="subtitle" style="text-transform: capitalize">
                    <fieldset class="aras1">
                        <legend> </legend>
                        <!--                        <p align="center">
                                                    <b>(MESYUARAT JAWATANKUASA TANAH DAERAH PADA <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" />)</b>
                                                </p>-->
                        <div class="content" align="center">                            
                            <table border="0" width="80%" cellspacing="10%" align="center">

                                <tr><td id="tdLabel" ><b>
                                            <font style="text-transform: capitalize">
                                                <tr><td>
                                                        <p><b>
                                                                <c:if test="${!actionBean.edit}">
                                                                    <s:textarea rows="6" cols="150" name="tajuk1" class="normal_text" style="text-transform: uppercase"/>
                                                                </c:if>
                                                                <c:if test="${actionBean.edit}">
                                                                    <span style="text-transform: uppercase">${actionBean.tajuk1}</span>
                                                                </c:if>
                                                            </b></p>
                                                    </td>
                                                </tr>
                                            </font></b></td></tr>
                                <tr><td>&nbsp;</td></tr>
                            </table>                            
                        </div>
                    </fieldset>
                </div>
            </td>
        </tr>
        <tr>
            <td width="1%"><b>1.</b></td>
            <td colspan="3"><div align="left"><b>TUJUAN</b></div></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">1.1</td>
            <td colspan="2">
                Tujuan kertas ini disediakan ialah untuk mendapatkan pertimbangan Mesyuarat Jawatankuasa Tanah Daerah bagi permohonan daripada:-
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td colspan="2">i) ${actionBean.convNama}</td>
        </tr>
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
            <tr>
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="2">ii) Untuk menduduki tanah kerajaan secara lesen pendudukan sementara.</td>
            </tr>
        </c:if>

        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
            <tr>
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="2">ii) Seluas lebih kurang ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}.</td>
            </tr>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' || actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
            <tr>
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="2">ii) Berisipadu ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}.</td>
            </tr>
        </c:if>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td colspan="2">iii) Di ${actionBean.convTempat}<span style="text-transform: capitalize"> ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}<c:if test="${actionBean.permohonan.cawangan.daerah.nama ne null}">,DAERAH ${actionBean.permohonan.cawangan.daerah.nama}</c:if></span></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                <td colspan="2">v) Untuk kegunaan ${actionBean.permitItem.kodItemPermit.nama}</td>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' || actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                <td colspan="2">iv) Untuk kegunaan <span style="text-transform: lowercase">${actionBean.permohonan.sebab}</span></td>
            </c:if>
        </tr>
        <tr>  
            <td colspan="4">
                &nbsp;
            </td>
        </tr>
        <tr>
            <td><b>2.</b></td>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                <td colspan="3"><b> LATAR BELAKANG TANAH</b></td>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' || actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                <td colspan="3"><b> LATAR BELAKANG TANAH BAWAH TANAH</b></td>
            </c:if>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%">2.1</td>
            <td colspan="2"> Tanah yang dimaksudkan seperti bertanda merah di dalam pelan ${actionBean.permohonan.idPermohonan}.</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%">&nbsp;</td>
            <td colspan="2"> i) Mukim :<span style="text-transform: capitalize"> ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}</span></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2"> ii) Tempat : ${actionBean.convTempat}</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2"> iii) Jenis Tanah : ${actionBean.permohonanLaporPelan.kodTanah.nama}</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2"> iv) Tanah Rizab : </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>
                <display:table  name="${actionBean.senaraiLaporanKawasan}" id="line9" class="tablecloth">
                    <s:hidden name="" class="${line9_rowNum -1}" value="${line9.idMohonlaporKws}"/>
                    <display:column title="No">${line9_rowNum}</display:column>
                    <display:column title="Jenis Rizab"  property="kodRizab.nama"/>
                    <display:column title="Catatan">
                        <c:if test="${line9.catatan ne null}">
                            ${line9.catatan}
                        </c:if>
                        <c:if test="${line9.catatan eq null}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="No Warta" property="noWarta"/>
                    <display:column title="Tarikh Warta" property="tarikhWarta" format="{0,date,dd-MM-yyyy}"/>
                    <display:column title="No Pelan Warta" property="noPelanWarta" />
                </display:table>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%" colspan="3">2.2 Keadaan tanahnya adalah
                <c:if test="${actionBean.laporanTanah.kodKeadaanTanah.kod eq 'LL'}">
                    ${actionBean.keadaantanah}xx
                </c:if>
                <c:if test="${actionBean.laporanTanah.kodKeadaanTanah.kod ne 'LL'}">
                    ${actionBean.laporanTanah.kodKeadaanTanah.nama}</td>
                </c:if>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td colspan="3">2.3 Keadaan sekeliling tanah adalah seperti berikut :-</td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">&nbsp;</td>
            <td colspan="2">
                <table class="tablecloth">
                    <tr>
                        <th>&nbsp;</th><th>Id. Hakmilik / No. Lot </th><th>Kegunaan Tanah</th><th>Milik Kerajaan</th>
                        <%--UTARA--%>
                        <c:if test="${empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU && empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnS && empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnT && empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnB}">
                        <tr><td colspan="4">Tiada Maklumat</td></tr>
                    </c:if>
                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}">
                        <c:set var="i" value="1" />
                        <tr>
                            <th rowspan="${actionBean.disLaporanTanahSempadan.uSize}">
                                Utara
                            </th>
                            <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}" var="line" >

                                <td>
                                    <c:if test="${line.laporanTanahSmpdn.noLot ne null}">
                                        ${line.laporanTanahSmpdn.kodLot.nama}&nbsp;${line.laporanTanahSmpdn.noLot}
                                        <s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.noLot" />
                                    </c:if>
                                    <c:if test="${line.laporanTanahSmpdn.noLot eq null}">
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                    </c:if>

                                </td>
                                <td>
                                    ${line.laporanTanahSmpdn.guna}
                                    <s:hidden   id="kandunganSpdnUKEG${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.guna" />
                                </td>
                                <td>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                    <s:hidden  name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnUMK${i}"/>

                                </td>
                            </tr>

                            <c:set var="i" value="${i+1}" />
                        </c:forEach>
                    </c:if>

                    <%--END OF UTARA--%>
                    <%--SELATAN--%>
                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnS}">
                        <c:set var="i" value="1" />
                        <tr>
                            <th rowspan="${actionBean.disLaporanTanahSempadan.sSize}">
                                Selatan
                            </th>
                            <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnS}" var="line">

                                <td>
                                    <c:if test="${line.laporanTanahSmpdn.noLot ne null}">
                                        ${line.laporanTanahSmpdn.kodLot.nama}&nbsp;${line.laporanTanahSmpdn.noLot}
                                        <s:hidden  id="kandunganSpdnSHM${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.hakmilik.noLot" />
                                    </c:if>
                                    <c:if test="${line.laporanTanahSmpdn.noLot eq null}">
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnSHM${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                    </c:if>
                                </td>
                                <td>
                                    ${line.laporanTanahSmpdn.guna}
                                    <s:hidden  id="kandunganSpdnSKEG${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.guna" />
                                </td>
                                <td>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                    <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnSMK${i}"/>

                                </td>
                            </tr>

                            <c:set var="i" value="${i+1}" />
                        </c:forEach>
                    </c:if>

                    <%--END OF SELATAN--%>
                    <%--TIMUR--%>
                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnT}">
                        <c:set var="i" value="1" />
                        <tr>
                            <th rowspan="${actionBean.disLaporanTanahSempadan.tSize}">
                                Timur
                            </th>
                            <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnT}" var="line">

                                <td>
                                    <c:if test="${line.laporanTanahSmpdn.noLot ne null}">
                                        ${line.laporanTanahSmpdn.kodLot.nama}&nbsp;${line.laporanTanahSmpdn.noLot}
                                        <s:hidden  id="kandunganSpdnTHM${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.hakmilik.noLot" />
                                    </c:if>
                                    <c:if test="${line.laporanTanahSmpdn.noLot eq null}">
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnTHM${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                    </c:if>
                                </td>
                                <td>
                                    ${line.laporanTanahSmpdn.guna}
                                    <s:hidden  id="kandunganSpdnTKEG${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.guna"  />
                                </td>
                                <td>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                    <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnTMK${i}" />

                                </td>
                            </tr>

                            <c:set var="i" value="${i+1}" />
                        </c:forEach>

                    </c:if>

                    <%--END OF TIMUR--%>
                    <%--BARAT--%>
                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnB}">
                        <c:set var="i" value="1" />
                        <tr>
                            <th rowspan="${actionBean.disLaporanTanahSempadan.bSize}">
                                Barat
                            </th>
                            <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnB}" var="line">

                                <td>
                                    <c:if test="${line.laporanTanahSmpdn.noLot ne null}">
                                        ${line.laporanTanahSmpdn.kodLot.nama}&nbsp;${line.laporanTanahSmpdn.noLot}
                                        <s:hidden  id="kandunganSpdnBHM${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.hakmilik.noLot" />
                                    </c:if>
                                    <c:if test="${line.laporanTanahSmpdn.noLot eq null}">
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnBHM${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                    </c:if>
                                </td>
                                <td>
                                    ${line.laporanTanahSmpdn.guna}
                                    <s:hidden  id="kandunganSpdnBKEG${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.guna"  />
                                </td>
                                <td>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                    <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnBMK${i}" />

                                </td>
                            </tr>

                            <c:set var="i" value="${i+1}" />
                        </c:forEach>

                    </c:if>

                    <%--END OF BARAT--%>

                </table>
            </td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>2.4</td>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                <td colspan="2">Perancangan Kerajaan : ${actionBean.permohonanLaporPelan.projekKerajaan}</td>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' || actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                <c:if test="${actionBean.laporanTanah.rancanganKerajaan ne null}">
                    <td colspan="2">Rancangan Tanah Kerajaan : ${actionBean.laporanTanah.rancanganKerajaan}</td>
                </c:if>
                <c:if test="${actionBean.laporanTanah.rancanganKerajaan eq null}">
                    <td colspan="2">Rancangan Tanah Kerajaan : Tidak</td>
                </c:if>
            </c:if>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>2.5</td>
            <td colspan="2">Butir-butir pemohon adalah seperti berikut :</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2">
                <table class="tablecloth">
                    <tr>
                        <th>Butir-Butir</th><th>Pemohon</th><th>Suami/Isteri</th>
                    </tr>
                    <tr>
                        <th>
                            Nama
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            ${actionBean.pihak.nama}
                        </td>
                        <td>
                            ${actionBean.pemohonHubungan.nama}
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            No. K/Pengenalan
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            ${actionBean.pihak.noPengenalan}
                        </td>
                        <td>
                            ${actionBean.pemohonHubungan.noPengenalan}
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            Alamat
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            ${actionBean.alamatPenuhPihak}
                        </td>
                        <td>
                            ${actionBean.alamatPenuhPhbgn}
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            T.Lahir
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <s:format formatPattern="dd/MM/yyyy" value="${actionBean.pihak.tarikhLahir}"/>
                        </td>
                        <td>
                            <s:format formatPattern="dd/MM/yyyy" value="${actionBean.pemohonHubungan.tarikhLahir}" />
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            Tempat Lahir
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <s:format formatPattern="dd/MM/yyyy" value="${actionBean.pihak.tempatLahir}"/>
                        </td>
                        <td>
                            <s:format formatPattern="dd/MM/yyyy" value="${actionBean.pemohonHubungan.tempatLahir}"/>
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            Pekerjaan
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            ${actionBean.pemohon.pekerjaan}
                        </td>
                        <td>
                            ${actionBean.pemohonHubungan.pekerjaan}
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            Pendapatan
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <c:if test="${actionBean.pemohon.pendapatan ne null}">RM ${actionBean.pemohon.pendapatan}</c:if>
                            <c:if test="${actionBean.pemohon.pendapatan eq null}"> -</c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.pemohon.pendapatan ne null}">RM ${actionBean.pemohonHubungan.gaji}</c:if>
                            <c:if test="${actionBean.pemohonHubungan.nama ne null}">
                                <c:if test="${actionBean.pemohon.pendapatan eq null}"> -</c:if>
                            </c:if>
                        </td>   
                    </tr>
                </table>
            </td>
        </tr>
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'RLPS' && actionBean.permohonan.kodUrusan.kod ne 'PBGSA' && actionBean.permohonan.kodUrusan.kod ne 'RLPSK'}">
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td><b>3.</b></td>
                <td colspan="3"><b> ULASAN JABATAN TEKNIKAL</b></td>
            </tr>
            <c:set var="i" value="1" />
            <c:forEach items="${actionBean.senaraiLaporanKandunganUtil}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top"><c:out value="3.${i}"/></td>
                    <td colspan="2"><font style="font-weight:bold;">${line.permohonanRujukanLuar.namaAgensi}</font></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td colspan="2">( Ruj. Surat ${line.permohonanRujukanLuar.noRujukan} bertarikh <s:format value="${line.permohonanRujukanLuar.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${!actionBean.edit}">
                            <s:textarea name="ulasan${i}" id="ulasan${i}"   class="normal_text" rows="6" cols="100" ></s:textarea>
                            <s:hidden name="rowAgensi${i}" value="${line.permohonanRujukanLuar.idRujukan}" id="rowAgensi${i}"/>
                        </c:if>
                        <c:if test="${actionBean.edit}">
                            Ulasan : ${line.permohonanRujukanLuar.ulasan}
                        </c:if>
                    </td>
                </tr>
                <c:set var="i" value="${i+1}" />
            </c:forEach>
        </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.editPTD}">
                <tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBGSA' && actionBean.permohonan.kodUrusan.kod ne 'RLPS' && actionBean.permohonan.kodUrusan.kod ne 'RLPSK'}">
                        <td width="1%"><b>4.</b></td>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBGSA' || actionBean.permohonan.kodUrusan.kod eq 'RLPS' || actionBean.permohonan.kodUrusan.kod eq 'RLPSK'}">
                        <td width="1%"><b>3.</b></td>
                    </c:if>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENOLONG PENTADBIR TANAH</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBGSA' && actionBean.permohonan.kodUrusan.kod ne 'RLPS' && actionBean.permohonan.kodUrusan.kod ne 'RLPSK'}">
                            <td valign="top"><c:out value="4.${i}"/></td>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBGSA' || actionBean.permohonan.kodUrusan.kod eq 'RLPS' || actionBean.permohonan.kodUrusan.kod eq 'RLPSK'}">
                            <td valign="top"><c:out value="3.${i}"/></td>
                        </c:if>
                        <td colspan="2">
                            <s:textarea  id="kandungan4${i}"name="senaraiLaporanKandungan1[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                </c:forEach>
                <s:hidden name="rowCount4" value="${fn:length(actionBean.senaraiLaporanKandungan1)}" id="rowCount4"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('4',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
            </c:if>
            <c:if test="${!actionBean.editPTD}">
                <tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBGSA' && actionBean.permohonan.kodUrusan.kod ne 'RLPS' && actionBean.permohonan.kodUrusan.kod ne 'RLPSK'}">
                        <td width="1%"><b>4.</b></td>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBGSA' || actionBean.permohonan.kodUrusan.kod eq 'RLPS' || actionBean.permohonan.kodUrusan.kod eq 'RLPSK'}">
                        <td width="1%"><b>3.</b></td>
                    </c:if>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENOLONG PENTADBIR TANAH </font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBGSA' && actionBean.permohonan.kodUrusan.kod ne 'RLPS'}">
                            <td valign="top"><c:out value="4.${i}"/></td>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBGSA' || actionBean.permohonan.kodUrusan.kod eq 'RLPS'}">
                            <td valign="top"><c:out value="3.${i}"/></td>
                        </c:if>

                        <td colspan="2">
                            ${line.kandungan}<s:hidden name="kandungan4${i}" id="kandungan2${i}"/>
                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                </c:forEach>
                <s:hidden name="rowCount4" value="${fn:length(actionBean.senaraiLaporanKandungan1)}" id="rowCount4"/>

                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>

            </c:if>
            <c:if test="${actionBean.editPTD}">
                <tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBGSA' && actionBean.permohonan.kodUrusan.kod ne 'RLPS' && actionBean.permohonan.kodUrusan.kod ne 'RLPSK'}">
                        <td width="1%"><b>5.</b></td>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBGSA' || actionBean.permohonan.kodUrusan.kod eq 'RLPS' || actionBean.permohonan.kodUrusan.kod eq 'RLPSK'}">
                        <td width="1%"><b>4.</b></td>
                    </c:if>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENOLONG PENTADBIR TANAH</font></b></div></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>Syor</td>
                    <td colspan="2">
                        <s:select name="kodKepu" id="keputsan" onchange="javaScript:changeHideSuku(this.value)">
                            <s:option value="0">Sila pilih</s:option>
                            <s:option value="L">diluluskan</s:option>
                            <s:option value="T">ditolak</s:option>
                        </s:select>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' || actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                            <b><s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></b>
                        </c:if>
                    </td>
                </tr>
            </c:if>
            <c:if test="${!actionBean.editPTD}">
                <tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBGSA' && actionBean.permohonan.kodUrusan.kod ne 'RLPS' && actionBean.permohonan.kodUrusan.kod ne 'RLPSK'}">
                        <td width="1%"><b>5.</b></td>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBGSA' || actionBean.permohonan.kodUrusan.kod eq 'RLPS' || actionBean.permohonan.kodUrusan.kod eq 'RLPSK'}">
                        <td width="1%"><b>4.</b></td>
                    </c:if>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENOLONG PENTADBIR TANAH</font></b></div></td>
                </tr>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS' && actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                    <tr>
                        <td>&nbsp;</td>
                        <td>Syor</td>
                        <td colspan="2">
                            <c:if test="${actionBean.kodKepu eq 'L'}">Diluluskan</c:if>
                            <c:if test="${actionBean.kodKepu eq 'T'}">Ditolak</c:if>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS' || actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                    <tr>
                        <td>&nbsp;</td>
                        <td width="1%">5.1</td>
                        <td colspan="3">Pentadbir Tanah Daerah ${actionBean.permohonan.cawangan.daerah.nama} mengesyorkan supaya permohonan ini
                            <c:if test="${actionBean.kodKepu eq 'L'}">diluluskan</c:if>
                            <c:if test="${actionBean.kodKepu eq 'T'}">ditolak</c:if>
                        </td>
                    </tr>
                </c:if>

                <c:if test="${actionBean.kodKepu eq 'T'}">

                </c:if>
            </c:if>
        </c:if>
    </table>
    <div id="suku">
        <table width="90%" border="0" >
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>a) Luas </td>
                    <td>
                        : Lebih kurang ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}.
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>b) Tempoh </td>
                    <td>
                        :
                        <c:if test="${actionBean.editPTD}">
                            <s:text id="tempoh" name="hakmilikPermohonan.tempohHakmilik" size="10"/> &nbsp; tahun
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.tempohHakmilik} &nbsp; tahun
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>c) Jenis Hakmilik Sementara</td>
                    <td> :
                        <c:if test="${actionBean.editPTD}">
                            <s:select name="kodHmlk" >
                                <s:option value="0">Sila Pilih</s:option>
                                <s:option value="HSM">Hakmilik Sementara (Mukim)</s:option>
                                <s:option value="HSD">Hakmilik Sementara Daftar</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            <c:if test="${actionBean.kodHmlk eq 'HSM'}">Hakmilik Sementara (Mukim)</c:if>
                            <c:if test="${actionBean.kodHmlk eq 'HSD'}">Hakmilik Sementara Daftar</c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>d) Jenis Hakmilik Tetap</td>
                    <td> :
                        <c:if test="${actionBean.editPTD}">
                            <s:select name="kodHmlk1" >
                                <s:option value="0">Sila Pilih</s:option>
                                <s:option value="GM">Geran Mukim</s:option>
                                <s:option value="PM">Pajakan Mukim</s:option>
                                <s:option value="GRN">Geran</s:option>
                                <s:option value="PN">Pajakan Negeri</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            <c:if test="${actionBean.kodHmlk1 eq 'GM'}">Geran Mukim</c:if>
                            <c:if test="${actionBean.kodHmlk1 eq 'PM'}">Pajakan Mukim</c:if>
                            <c:if test="${actionBean.kodHmlk1 eq 'GRN'}">Geran</c:if>
                            <c:if test="${actionBean.kodHmlk1 eq 'PN'}">Pajakan Negeri</c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>e) Kadar Cukai </td>
                    <td> : Mengikut NSPU 7/2005</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>f) Kadar Premium </td>
                    <td> : Mengikut NSPU 25/2002 dan Jabatan Penilaian dan Perkhidmatan Harta</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>g) Kadar Bayaran Upah Ukur dan Batu Sempadan </td>
                    <td> : Mengikut Jadual
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">(P.U.[A]438)</c:if></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>h)Kadar Bayaran Pendaftaran dan Penyediaaan Hakmilik Sementara / Tetap </td>
                    <td> : Mengikut Peraturan - Peraturan tanah Negeri</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>i)Kategori </td>
                    <td> : <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru ne ''}">${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</c:if></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>j)Syarat Nyata </td>
                    <td>:
                        <c:if test="${actionBean.editPTD}">
                            <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                            <s:hidden name="kod" id="kod"/>
                            <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}&nbsp;
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>k)Sekatan Kepentingan </td>
                    <td> :
                        <c:if test="${actionBean.editPTD}">
                            <s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                            <s:hidden name="kodSktn" id="kodSktn"/>
                            <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}&nbsp;
                        </c:if>
                    </td>
                </tr>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'RLPSK'}">
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>a) Lesen </td>
                    <td> : Lesen Pendudukan Sementara </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>b) Kegunaan </td>
                    <td> : Untuk ${actionBean.permitItem.kodItemPermit.nama} sahaja </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>c) Keluasan </td>
                    <td> : Lebih kurang ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}.</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td width="15%">d) Kadar Bayaran (RM)</td>
                    <td> : <s:text name="bayaran" onkeyup="validateNumber(this,this.value);"/>  </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>e) Syarat-syarat </td>
                    <td> : </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td> i) Lesen Pendudukan Sementara ini hendaklah diperbaharui pada setiap awal tahun dan akan tamat tempoh pada 31 Disember tiap-tiap tahun. </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>ii) Tanah ini tidak boleh digunakan bagi apa-apa maksud selain daripada maksud yang dinyatakan di dalamnya.</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>iii) Lesen Pendudukan Sementara ini tidak boleh dipajak, dijual atau disewakan kepada sesiapa jua pun.</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>iv) Bangunan kekal tidak dibenarkan didirikan di atas tanah ini.
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>v)Lesen Pendudukan Sementara ini akan dibatalkan dan tanah ini akan diambil balik tanpa apa-apa gantirugi apabila Kerajaan hendak menggunakan
                        tanah ini kelak atau jika berlaku apa-apa perlanggaran syarat terhadap tanah ini.
                    </td>
                </tr>
            </c:if>
        </table>
    </div>
    <div id="ta">
        <table width="90%" border="0" >
            <tr>
                <td width="5%">&nbsp;</td>
                <td colspan="3">
                    <c:if test="${actionBean.editPTD}">
                        <s:textarea name="syor" rows="7" cols="150"/>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        <span><font style="text-transform: capitalize">${actionBean.fasaPermohonan.ulasan}</font></span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="4"></td>
            </tr>
        </table>
    </div>
    <table width="90%" border="0" >
        <br>
        <tr>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBGSA' && actionBean.permohonan.kodUrusan.kod ne 'RLPS' && actionBean.permohonan.kodUrusan.kod ne 'RLPSK'}">
                <td width="1%"><b>6.</b></td>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBGSA' || actionBean.permohonan.kodUrusan.kod eq 'RLPS' || actionBean.permohonan.kodUrusan.kod eq 'RLPSK'}">
                <td width="1%"><b>5.</b></td>
            </c:if>
            <td colspan="3"><div align="left"><b>&nbsp;&nbsp;KEPUTUSAN</b></div></td>
        </tr>
        <tr>
            <td width="1%">&nbsp;</td>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBGSA' && actionBean.permohonan.kodUrusan.kod ne 'RLPS' && actionBean.permohonan.kodUrusan.kod ne 'RLPSK'}">
                <td>6.1  Dibentangkan untuk pertimbangan dan keputusan Jawatankuasa Tanah Daerah.</td>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBGSA' || actionBean.permohonan.kodUrusan.kod eq 'RLPS' || actionBean.permohonan.kodUrusan.kod eq 'RLPSK'}">
                <td>5.1  Dibentangkan untuk pertimbangan dan keputusan Jawatankuasa Tanah Daerah.</td>
            </c:if>

            <%--<td>Dibentangkan untuk pertimbangan dan keputusan Jawatankuasa Tanah Daerah.</td>--%>
        </tr>
    </table>
    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
        <c:if test="${actionBean.editPTD}">
            <p align="center">
                <s:button name="simpan" id="save" value="Simpan1" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </c:if>
    </c:if>

</s:form>

