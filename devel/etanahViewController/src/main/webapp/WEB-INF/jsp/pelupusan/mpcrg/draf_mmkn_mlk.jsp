<%-- 
    Document   : draf_mmkn__mlk
    Created on : 
    Author     : HLS Creative
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<style type="text/css">
    #tdLabel {
        color:#003194;
        float:left;
        font-family:Tahoma;
        font-size:14px;
        font-weight:bold;
    }

    #tdDisplay {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:14px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        width:14em;
    }

    #test {
        text-transform: capitalize;
    }
</style>
<script type="text/javascript">
    
    $(document).ready(function() {
            var perakuan = "${actionBean.peraku}";
            if(perakuan == 'SL'){
                $('#akuan').show();
                $('#akuan2').show();
            }
            
             if(perakuan == 'ST'){
                $('#akuan').hide();
                $('#akuan2').hide();
            }
            
            var kodhm = "${actionBean.kodHmlk}";
//            alert(kodhm);
            if (kodhm == 'GRN' || kodhm == 'GM'){
                $('#jikaPajakan').hide(); 
                 $('#premium1').show();
                $('#premium2').hide() ;
                 $('#hasil1').show();
                $('#hasil2').hide() ;
                 $('#bu1').show();
                $('#bu2').hide() ;
                 $('#jenis1').show();
                $('#jenis2').hide() ;
                 $('#sn1').show();
                $('#sn2').hide() ;
                $('#sk1').show();
                $('#sk2').hide() ;
            }else{
                $('#jikaPajakan').show();
                 $('#premium2').show();
                $('#premium1').hide() ;
                 $('#hasil2').show();
                $('#hasil1').hide() ;
                 $('#bu2').show();
                $('#bu1').hide() ;
                 $('#jenis2').show();
                $('#jenis1').hide() ;
                 $('#sn2').show();
                $('#sn1').hide() ;
                $('#sk2').show();
                $('#sk1').hide() ;
            }
       });

    function managepajakan(val){
        
            if(val=='GRN'|| val=='GM'){
                //alert(val);
                $('#jikaPajakan').hide();
                $('#premium1').show();
                $('#premium2').hide() ;
                 $('#hasil1').show();
                $('#hasil2').hide() ;
                 $('#bu1').show();
                $('#bu2').hide() ;
                 $('#jenis1').show();
                $('#jenis2').hide() ;
                 $('#sn1').show();
                $('#sn2').hide() ;
                $('#sk1').show();
                $('#sk2').hide() ;
            }else if (val=='PN' || val=='PM'){
                //alert(val);
                $('#jikaPajakan').show();
                 $('#premium2').show();
                $('#premium1').hide() ;
                 $('#hasil2').show();
                $('#hasil1').hide() ;
                 $('#bu2').show();
                $('#bu1').hide() ;
                 $('#jenis2').show();
                $('#jenis1').hide() ;
                 $('#sn2').show();
                $('#sn1').hide() ;
                $('#sk2').show();
                $('#sk1').hide() ;
            }
            
        }
        
    function addRow(tableid) {

        // alert(tableID);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "2.1"+ "." +(rowCount+3);

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan1"+(rowCount+3);
        element1.rows = 6;
        element1.cols = 150;
        element1.name ="kandungan1"+(rowCount+3);
        element1.id ="kandungan1"+(rowCount+3);
        cell1.appendChild(element1);

        document.getElementById("rowCount3").value=rowCount+3;
    }

    function addRow1(tableid) {

        // alert(tableid);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "6."+(rowCount+1);

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan2"+(rowCount+1);
        element1.rows = 6;
        element1.cols = 150;
        element1.name ="kandungan2"+(rowCount+1);
        element1.id ="kandungan2"+(rowCount+1);
        cell1.appendChild(element1);

        document.getElementById("rowCount4").value=rowCount+1;
    }

    function addRow2(tableid) {

        // alert(tableID);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "7."+(rowCount+1);

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan3"+(rowCount+1);
        element1.rows = 6;
        element1.cols = 150;
        element1.name ="kandungan3"+(rowCount+1);
        element1.id ="kandungan3"+(rowCount+1);
        cell1.appendChild(element1);

        document.getElementById("rowCount5").value=rowCount+1;
    }

    function addRow3(tableid) {

        // alert(tableID);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "5.1"+ "." +(rowCount+2);

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan5"+(rowCount+2);
        element1.rows = 6;
        element1.cols = 148;
        element1.name ="kandungan5"+(rowCount+2);
        element1.id ="kandungan5"+(rowCount+2);
        cell1.appendChild(element1);

        document.getElementById("rowCount6").value=rowCount+2;
    }


    ///aded

    function cariPopup(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmk_mpcrg?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }

    function cariPopup2(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmk_mpcrg?showFormCariKodSekatan';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }

    function doFunc(val){           
        if (val == "SL"){
            $('#akuan').show();
            $('#akuan2').show();
        }
        
        if (val == "ST"){
            $('#akuan').hide();
            $('#akuan2').hide();
        }
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMK_MPCRGActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.idKertas"/>
    <s:hidden name="edit" id="edit"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="10%">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MPCRG' }">
                        <tr>
                            <td id="tdLabel" >
                                <b>
                                    <font style="text-transform: capitalize">
                                        <tr>
                                            <td>
                                                <c:if test="${actionBean.edit}">
                                                    <table width="100%">
                                                        <tr>
                                                            <td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                            <td><s:textarea rows="6" cols="150" name="tajuk" style="text-transform:uppercase;font-weight:bold;" class="normal_text" /></td>
                                                        </tr>
                                                    </table>
                                                </c:if>
                                                <c:if test="${!actionBean.edit}">
                                                    <table width="100%">
                                                        <tr>
                                                            <td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                            <td> <span style="text-transform:uppercase;font-weight:bold;">${actionBean.tajuk}</span> </td>
                                                        </tr>
                                                    </table>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </font>        
                                </b>
                            </td>
                        </tr>
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
                        <tr><td id="tdLabel" width="100%"><b>1. TUJUAN</b></td></tr>
                        <c:if test="${actionBean.edit}">
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr>
                                        <td valign="top">1.1 </td>
                                        <td> <s:textarea rows="6" cols="150" name="tujuan" class="normal_text"/></td>
                                    </tr>
                                </table>    
                            </td>
                        </tr>    
                        </c:if>
                        <c:if test="${!actionBean.edit}">
                            <tr>
                                <td>
                                    <table width="100%">
                                        <tr>
                                            <td valign="top">1.1 </td>
                                            <td>&nbsp;${actionBean.tujuan}</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td>
                            <table border="0" width="100%" id="tblhuraian" cellspacing="0">
                                <tr><td id="tdLabel"><b><font style="text-transform: capitalize">2. LATAR BELAKANG</font></b></td></tr>
                                <tr><td>&nbsp;</td></tr>
                                <tr>
                                    <td id="tdLabel"><b>2.1 Perihal Permohonan</b></td>
                                </tr>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MPJLT' }">
                                    <tr><td>&nbsp;</td></tr>
                                    <c:if test="${actionBean.edit}">
                                        <tr>
                                            <td>
                                                <table width="100%">
                                                    <tr>
                                                        <td valign="top">2.1.1 </td>
                                                        <td> <s:textarea rows="6" cols="150" name="perihalpermohonan" class="normal_text"/></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${!actionBean.edit}">
                                        <tr>
                                            <td>
                                                <table width="100%">
                                                    <tr>
                                                        <td valign="top">2.1.1 </td>
                                                        <td>&nbsp;${actionBean.perihalpermohonan}</td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                    <tr>
                                        <td>
                                            <table width="100%">
                                                <tr>
                                                    <td valign="top">2.1.2 </td>
                                                    <td> <s:textarea rows="6" cols="150" name="perihalpermohonan1" class="normal_text" /></td></tr>
                                            </table>
                                        </td>
                                    </tr>
                                    </c:if>
                                    <c:if test="${!actionBean.edit}">
                                    <tr>
                                        <td>
                                            <table width="100%">
                                            <tr>
                                                <td valign="top">2.1.2</td>
                                                <td>&nbsp;${actionBean.perihalpermohonan1}</td>
                                            </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    </c:if>
                                </c:if>
                            </table>
                            <tr>
                                <td>
                                <c:if test="${actionBean.edit}">
                                <table id ="dataTable1" border="0">
                                    <c:set var="i" value="3" />
                                    <c:set var="k" value="3" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line" begin="2">
                                        <tr>
                                            <td style="display:none" valign="top">${line.idKandungan}</td>
                                            <td valign="top"><c:out value="2.1.${k}"/></td>
                                            <td valign="top">
                                                <font style="text-transform: capitalize">
                                                    <s:textarea name="kandungan1${i}" id="kandungan1${i}"  rows="6" cols="150" >${line.kandungan}</s:textarea>
                                                </font>
                                            </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                                </table>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable1',3)"/>&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:hidden name="rowCount3" value="${fn:length(actionBean.senaraiLaporanKandungan1)}" id="rowCount3"/>&nbsp;
                                </td>
                            </tr>
                            </c:if>

                        <tr>
                            <td>
                            <c:if test="${actionBean.edit}">
                                <table id ="dataTable1" border="0" cellspacing="12">
                                    <c:set var="i" value="3" />
                                    <c:set var="k" value="3" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line" begin="2">
                                        <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="2.1.${k}"/> </td>
                                            <td valign="top"><font style="text-transform: capitalize">${line.kandungan}</font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                </table>
                            </c:if>
                            </td>
                        </tr>

                        <tr><td id="tdLabel"><b>2.2 Perihal Tanah</b></td></tr>
                        <tr>
                            <td>
                                <table width="100%" border="0">
                                <c:if test="${actionBean.edit}">
                                    <tr>
                                        <td valign="top">2.2.1 </td>
                                        <td> <s:textarea rows="6" cols="150" name="perihaltanah1" class="normal_text" value="${actionBean.perihaltanah1}" /></td>
                                    </tr>
                                </c:if>
                                <c:if test="${!actionBean.edit}">
                                    <tr><td>2.2.1</td>
                                        <td>${actionBean.perihaltanah1}</td></tr>
                                </c:if>
                                </table>
                            </td>
                        </tr>

                    <tr>
                        <td>
                            <table width="100%">
                                <c:if test="${actionBean.edit}">
                                    <tr><td valign="top">2.2.2 </td>
                                        <td> <s:textarea rows="6" cols="150" name="perihaltanah" class="normal_text" /></td></tr>
                                    </c:if>

                                <c:if test="${!actionBean.edit}">
                                    <tr><td valign="top">2.2.2 </td>
                                        <td>&nbsp;${actionBean.perihaltanah}</td></tr>
                                    </c:if>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td valign="top">2.2.3
                                        Tanah-tanah yang bersempadan dengan tanah yang dipohon adalah seperti berikut:- 
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    
                    <tr><td colspan="6">
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.laporanTanahSempadanList}" cellpadding="0" cellspacing="0"
                                               requestURI="/pelupusan/draf_mmkn_bmbt" id="line">
                                    <display:column title="Kedudukan">
                                        <c:if test="${line.jenisSempadan eq 'U'}">
                                            Utara
                                        </c:if>
                                        <c:if test="${line.jenisSempadan eq 'S'}">
                                            Selatan
                                        </c:if>
                                        <c:if test="${line.jenisSempadan eq 'B'}">
                                            Barat
                                        </c:if>
                                        <c:if test="${line.jenisSempadan eq 'T'}">
                                            Timur
                                        </c:if>
                                    </display:column>
                                    <display:column title="Tanah Kerajaan/Rizab/Lot/PT" >
                                        <c:if test="${line.milikKerajaan eq 'T' || line.milikKerajaan eq 'N'}">
                                            <c:if test="${line.noLot ne null && line.kodLot ne null}">
                                                ${line.kodLot.nama} ${line.noLot}
                                            </c:if>
                                        </c:if>
                                        <c:if test="${line.milikKerajaan eq 'Y'}">
                                            Tanah Kerajaan
                                        </c:if>
                                        <c:if test="${line.milikKerajaan eq 'R'}">
                                            Rizab
                                        </c:if>

                                    </display:column>
                                    <display:column title="Apa yang terdapat di atas tanah">Tanah ini digunakan untuk ${line.guna} dan keadaannya adalah ${line.keadaanTanah}</display:column>
                                </display:table>

                            </div>
                        </td></tr>

                    <tr><td id="tdLabel"><b>2.3 Perihal Pemohon</b></td></tr>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'|| actionBean.pihak.jenisPengenalan.kod eq 'L'|| actionBean.pihak.jenisPengenalan.kod eq 'P'||actionBean.pihak.jenisPengenalan.kod eq 'T'||actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'N'||actionBean.pihak.jenisPengenalan.kod eq 'F'}">
                        <tr><td>
                                <table width="100%">
                                    <c:if test="${actionBean.edit}">
                                        <tr>
                                            <td valign="top">2.3.1 </td>
                                            <td> <s:textarea rows="6" cols="150" name="perihalpemohon"  value="${actionBean.perihalpemohon}" class="normal_text" /></td>
                                        </tr>
                                    </c:if>

                                    <c:if test="${!actionBean.edit}">
                                        <tr>
                                            <td valign="top">2.3.1 </td>
                                            <td>${actionBean.perihalpemohon}</td>
                                        </tr>
                                    </c:if>
                                </table>
                            </td>
                        </tr>
                    </c:if>

                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                        <tr><td><table width="100%">
                                    <c:if test="${actionBean.edit}">
                                        <tr><td valign="top">2.3.1 </td>
                                            <td> <s:textarea rows="6" cols="150" name="perihalpemohon_s" value="${actionBean.perihalpemohon_s}" class="normal_text" /></td></tr>
                                        </c:if>

                                    <c:if test="${!actionBean.edit}">
                                        <tr><td valign="top">2.3.1 </td>
                                            <td>${actionBean.perihalpemohon_s}</td></tr>
                                        </c:if>
                                </table>
                            </td>
                        </tr>
                    </c:if>

                    <c:if test="${actionBean.pihak.jenisPengenalan.kod ne 'S'}">
                        <tr><td><table width="100%">
                                    <c:if test="${actionBean.edit}">
                                        <tr><td valign="top">2.3.2 </td>
                                            <td> <s:textarea rows="6" cols="150" name="perihalpemohon1" value="${actionBean.perihalpemohon1}" class="normal_text" /></td></tr>
                                        </c:if>

                                    <c:if test="${!actionBean.edit}">
                                        <tr><td valign="top">2.3.2 </td>
                                            <td>${actionBean.perihalpemohon1}</td></tr>
                                        </c:if>
                                </table></td></tr>
                            </c:if>

                    <c:if test="${actionBean.pemohon.statusKahwin eq 'Berkahwin'}">
                        <c:if test="${actionBean.pemohonHubungan.kaitan eq 'ISTERI'}">
                            <tr><td><table width="100%">
                                        <c:if test="${actionBean.edit}">
                                            <tr><td valign="top">2.3.3 </td>
                                                <td> <s:textarea rows="6" cols="150" name="perihalpemohon2" class="normal_text" /></td></tr>
                                            </c:if>

                                        <c:if test="${!actionBean.edit}">
                                            <tr><td valign="top">2.3.3 </td>
                                                <td>${actionBean.perihalpemohon2}</td></tr>
                                            </c:if>
                                    </table></td></tr>
                                </c:if>

                        <c:if test="${actionBean.pemohonHubungan.kaitan eq 'SUAMI'}">
                            <tr><td><table width="100%">
                                        <c:if test="${actionBean.edit}">
                                            <tr><td valign="top">2.3.3 </td>
                                                <td> <s:textarea rows="6" cols="150" name="perihalpemohon2suami" class="normal_text" /></td></tr>
                                            </c:if>

                                        <c:if test="${!actionBean.edit}">
                                            <tr><td valign="top">2.3.3 </td>
                                                <td>${actionBean.perihalpemohon2suami}</td></tr>
                                            </c:if>
                                    </table></td></tr>
                                </c:if>
                            </c:if>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td id="tdLabel"><b><font style="text-transform: capitalize">3. ULASAN JABATAN-JABATAN TEKNIKAL</font></b></td></tr>
                    <tr><td>
                            <table id ="dataTable2">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiLaporanKandunganUtil}" var="line">
                                    <c:if test="${actionBean.edit}">
                                        <tr>
                                                <td valign="top">&nbsp;&nbsp;&nbsp; <c:out value="3.${i}"/>&nbsp;</td>
                                                <td><font style="font-weight:bold;">${line.permohonanRujukanLuar.namaAgensi}</font></td>
                                        </tr>
                                        <tr>
                                                <td valign="top">&nbsp;</td>
                                                <td colspan="2">( Ruj. Surat ${line.permohonanRujukanLuar.noRujukan} bertarikh <s:format value="${line.permohonanRujukanLuar.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>${line.permohonanRujukanLuar.ulasan}<font><!--s:textarea name="ulasan${i}" id="ulasan${i}"   class="normal_text" rows="6" cols="150"-- >${line.permohonanRujukanLuar.ulasan}<!--/s:textarea--></font></td>
                                            <s:hidden name="rowAgensi${i}" value="${line.permohonanRujukanLuar.idRujukan}" id="rowAgensi${i}"/>
                                        </tr>
                                     </c:if>
                                     <c:if test="${!actionBean.edit}">
                                         <tr>
                                            <td valign="top">&nbsp;&nbsp;&nbsp; <c:out value="3.${i}"/></td>
                                            <td><font style="font-weight:bold;">${line.permohonanRujukanLuar.namaAgensi}</font></td>
                                         </tr>
                                         <tr>
                                            <td valign="top">&nbsp;</td>
                                            <td colspan="2">( Ruj. Surat ${line.permohonanRujukanLuar.noRujukan} bertarikh <s:format value="${line.permohonanRujukanLuar.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>${line.permohonanRujukanLuar.ulasan}<font><!--s:textarea name="ulasan${i}" id="ulasan${i}"   class="normal_text" rows="6" cols="150"-- >${line.permohonanRujukanLuar.ulasan}<!--/s:textarea--></font></td>
                                            <s:hidden name="rowAgensi${i}" value="${line.permohonanRujukanLuar.idRujukan}" id="rowAgensi${i}"/>
                                        </tr>
                                    </c:if>
                               <c:set var="i" value="${i+1}" />
                                </c:forEach>
                    </table></td></tr>
                <tr><td><s:hidden name="rowCount1" value="${fn:length(actionBean.senaraiLaporanKandunganUtil)}" id="rowCount1"/>&nbsp;</td></tr>

                <tr><td id="tdLabel"><b><font style="text-transform: capitalize">4. ULASAN Y.B ADUN KAWASAN</font></b></td></tr>
                <tr><td><table width="100%">
                            <c:if test="${actionBean.edit}">
                                <tr>
                                    <td valign="top">&nbsp;&nbsp;&nbsp;4.1</td>
                                    <td>
                                        <font style="text-transform: capitalize">
                                            <s:textarea rows="4" cols="150" name="ulasanyb" value="${actionBean.ulasanyb}" class="normal_text" />
                                        </font>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${!actionBean.edit}">
                                <tr><td valign="top">&nbsp;&nbsp;&nbsp;4.1</td>
                                    <td>${actionBean.ulasanyb}</td></tr>
                                </c:if>
                        </table></td></tr>

                <tr><td>&nbsp;</td></tr>
                <tr><td id="tdLabel"><b><font style="text-transform: capitalize">5. PERAKUAN PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}&nbsp;</font></b></td></tr>
                <c:if test="${actionBean.edit}">
                    <c:if test="${actionBean.ptd}">
                    <tr>
                        <td id="tdLabel" valign="top"><b>PERAKUAN :</b>
                            <s:radio name="peraku" id="peraku" value="SL" onclick="doFunc(this.value);" />&nbsp;Syor Lulus
                            <s:radio name="peraku" id="peraku" value="ST" onclick="doFunc(this.value);" />&nbsp;Syor Tolak
                            <s:hidden name="syorKpsn" id="syorKpsn" value=""/>
                        </td> 
                    </tr>
                    </c:if>
                 </c:if>
                    <tr><td><table width="100%">
                            <c:if test="${actionBean.edit or actionBean.ptd}">
                                <tr><td valign="top">5.1 </td>
                                    <td> <s:textarea rows="6" cols="150" name="pentabirtanah2" value="${actionBean.pentabirtanah2}" class="normal_text" /></td></tr>
                             </c:if>

                            <c:if test="${!actionBean.edit and !actionBean.ptd}">
                                <tr><td valign="top">5.1 </td>
                                    <td>&nbsp;${actionBean.pentabirtanah2}</td></tr>
                                </c:if>

                        </table></td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr class="akuan" id="akuan"><td id="tdLabel"><b>I.<u> Syarat-syarat Hakmilik</u></b></td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr>
                        <td>
                            <table border="0" width="80%" cellspacing="10" class="akuan2" id="akuan2">
                        <tr>
                            <td id="tdDisplay">a) Jenis Hakmilik </td>
                            <td width="2%"><b> : </b></td>
                            <td>
                                <c:if test="${actionBean.edit}">
                                    <s:select name="kodHmlk" value="${actionBean.kodHmlk} -  ${actionBean.hakmilikPermohonan.kodHakmilik.nama}" onchange="managepajakan(this.value)">
                                        <s:option value="0">--Sila Pilih--</s:option>
                                        <s:option value="GRN">Geran (Pendaftar)</s:option>
                                        <s:option value="PN">Pajakan Negeri (Pendaftar)</s:option> 
                                        <s:option value="GM">Geran Mukim (Pejabat Tanah)</s:option>
                                        <s:option value="PM">Pajakan Mukim (Pejabat Tanah)</s:option>
                                    </s:select>
                                </c:if>
                                <c:if test="${!actionBean.edit}">
                                    ${actionBean.kodHmlk} -  ${actionBean.hakmilikPermohonan.kodHakmilik.nama}
                                </c:if>
                            </td>
                        </tr>
                        <tr id="jikaPajakan">
                        
                            <td id="tdDisplay">b) Tempoh Pajakan</td>
                            <td width="2%"><b> : </b></td>
                            <td>
                                <c:if test="${actionBean.edit}">
                                    <s:text id="tempoh" name="hakmilikPermohonan.tempohPajakan" value="${actionBean.hakmilikPermohonan.tempohPajakan}" size="10"/> &nbsp; tahun
                                </c:if>
                                <c:if test="${!actionBean.edit}">
                                    ${actionBean.hakmilikPermohonan.tempohPajakan} &nbsp; tahun
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdDisplay">
                                <div id="premium1">b) Premium</div>
                            </td>
                            <td width="2%"><b> : </b></td>
                            <c:if test="${actionBean.edit}">
                            <%--<td><s:text id="premium" name="hakmilikPermohonan.catatan" size="10"/> X harga pasaran X tempoh pajakan</td>--%>
                            <td>
                                <s:select name="hakmilikPermohonan.keteranganKadarPremium" id="nama">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.listSenaraikkp}" label="nama" value="nama"/>
                                </s:select></td>
                            </c:if>
                            <c:if test="${!actionBean.edit}">
                                    <%--<td> ${actionBean.hakmilikPermohonan.catatan} X harga pasaran X tempoh pajakan </td>--%>
                                    <td> ${actionBean.hakmilikPermohonan.keteranganKadarPremium} </td>
                            </c:if>
                        </tr>
                         <tr>						
                             <td id="tdDisplay">
                                 <div id="hasil1">c) Hasil</div>
                             </td>
                                <td width="2%"><b> :  </b></td>
                                <c:if test="${actionBean.edit}">
                                    <%--<td>RM<s:text name="hakmilikPermohonan.cukaiPerMeterPersegi" size="5"/>  bagi tiap-tiap 100 m.p atau Sebahagian daripadanya (RM <s:text name="hakmilikPermohonan.cukaiPerLot" size="5"/> bagi setiap hektar)</td>--%>
                                    <td>RM <s:text name="hakmilikPermohonan.keteranganCukaiBaru" size="10" class="normal_text"/></td>
                                </c:if>
                                <c:if test="${!actionBean.edit}">
                                    <%--<td> RM  ${actionBean.hakmilikPermohonan.cukaiPerMeterPersegi}  bagi tiap-tiap 100 m.p atau Sebahagian daripadanya (RM ${actionBean.hakmilikPermohonan.cukaiPerLot} bagi setiap hektar)</td>--%>
                                    <td>RM ${actionBean.hakmilikPermohonan.keteranganCukaiBaru}</td>
                                </c:if>
                         </tr>

                            <tr>
                                 <td id="tdDisplay">
                                    <div id="bu1">d) Bayaran Ukur</div>
                                 </td>
                                <c:if test="${actionBean.edit}">
                               
                                <td width="2%"><b> : </b></td>
                                <td><s:radio name="hakmilikPermohonan.agensiUpahUkur" id="bayaranUkur" value="JUP"/> Mengikut P.U (A) 438
                                    <s:radio name="hakmilikPermohonan.agensiUpahUkur" id="bayaranUkur" value="JUB"/> Juru Ukur Lesen
                                </td>
                                </c:if>
                                <c:if test="${!actionBean.edit}">
                                    <td width="2%"><b> : </b></td>
                                    <td>
                                        <c:if test="${actionBean.hakmilikPermohonan.agensiUpahUkur eq 'JUP'}">Mengikut P.U (A) 438</c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.agensiUpahUkur eq 'JUB'}">Juru Ukur Lesen</c:if>
                                    </td>
                                </c:if>
                            </tr>
                            <tr>
                                <c:if test="${actionBean.edit}">
                                <td id="tdDisplay">e) Kategori</td>         
                                <td width="2%"><b> : </b></td>
                                <td> <s:select name="hakmilikPermohonan.penjenisan" id="hakmilikPenjenisan" value="${hakmilikPermohonan.penjenisan}">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="01">Bangunan</s:option>
                                        <s:option value="02">Perusahaan</s:option>
                                        <s:option value="03">Pertanian</s:option>
                                    </s:select></td>
                                </c:if>
                                <c:if test="${!actionBean.edit}">
                                    <td id="tdDisplay">e) Kategori</td>
                                    <td width="2%"><b> : </b></td>
                                    <td>
                                        <c:if test="${actionBean.hakmilikPermohonan.penjenisan eq '01'}">Bangunan</c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.penjenisan eq '02'}">Perusahaan</c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.penjenisan eq '03'}">Pertanian</c:if>
                                    </td>
                                </c:if>
                            </tr>
                            <tr>
                                <td width="20%" style="color: #003194; font-weight: 700">
                                    <div id="sn1">f) Syarat Nyata</div>
                                </td>
                                <td ><b> : </b></td>
                                <td>
                                    <c:if test="${actionBean.edit}"> 
                                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}--${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                                        <s:hidden name="kod" id="kod"/><br>
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/>
                                    </c:if>
                                    <c:if test="${!actionBean.edit}">
                                        ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}
                                    </c:if>
                                </td>
                            </tr>

                            <tr>
                                <td width="20%" style="color: #003194; font-weight: 700">
                                    <div id="sk1">g) Sekatan Kepentingan</div>
                                </td>
                                <td ><b> : </b></td>
                                <td align="left">
                                    <c:if test="${actionBean.edit}">
                                        <s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod}--${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
                                        <s:hidden name="kodSktn" id="kodSktn"/><br>
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/>
                                    </c:if>
                                    <c:if test="${!actionBean.edit}">
                                        ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%">
                            <c:if test="${actionBean.edit or actionBean.ptd}">                               
                                <tr>
                                    <td valign="top">5.2 </td>
                                    <td> <s:textarea rows="6" cols="150" name="pentabirtanah1" value="${actionBean.pentabirtanah1}" class="normal_text" /></td>
                                </tr>
                            </c:if>
                            <c:if test="${!actionBean.edit and !actionBean.ptd}">
                                <tr>
                                    <td valign="top">5.2 </td>
                                    <td>&nbsp;${actionBean.pentabirtanah1}</td>
                                <tr>
                                </c:if>
                            </table>
                        </td>
                    </tr>

                <tr><td>
                        <c:if test="${actionBean.edit or actionBean.ptd}">
                            <table id ="dataTable5" border="0">
                                <c:set var="i" value="2" />
                                <c:set var="k" value="2" />
                                <c:forEach items="${actionBean.senaraiLaporanKandunganpbtanah}" var="line">
                                    <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="5.2.${k}"/></td>
                                        <td valign="top"><font style="text-transform: capitalize"><s:textarea name="kandungan5${i}" id="kandungan5${i}"  rows="6" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                            </table></td></tr>
                    <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable5',2)"/>&nbsp;
                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button></td>
                    </tr>
                    <tr><td><s:hidden name="rowCount6" value="${fn:length(actionBean.senaraiLaporanKandunganpbtanah)}" id="rowCount6"/>&nbsp;</td></tr>

                </c:if>

                <tr><td>
                        <c:if test="${!actionBean.edit and !actionBean.ptd}">
                            <table id ="dataTable5" border="0" cellspacing="12">
                                <c:set var="i" value="2" />
                                <c:set var="k" value="2" />
                                <c:forEach items="${actionBean.senaraiLaporanKandunganpbtanah}" var="line">
                                    <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="5.1.${k}"/> </td>
                                        <td valign="top"><font style="text-transform: capitalize">${line.kandungan}</font>
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                            </table>
                        </c:if>
                    </td>
                </tr>
                    <tr>


                        <c:if test="${actionBean.pemohon.pihak.bangsa.kod ne 'MEL' }">
                        <tr><td id="tdLabel">II. <u>Syarat Am</u></td></tr>

                        <tr>
                            <td> - Tiada </td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.pemohon.pihak.bangsa.kod eq 'MEL' }">
                        <tr><td id="tdLabel">II. <u>Syarat Am</u></td></tr>

                        <tr>
                            <td> Setelah hakmilik didaftarkan, pemilik tanah ini dikehendaki membuat permohonan untuk menjadikan hakmiliknya kepada Tanah Adat Melaka (MCL).</td>
                        </tr>
                    </c:if>

                <c:if test="${!actionBean.edit}">
                    <c:if test="${actionBean.ptg}">
                        
                        <tr><td>&nbsp;</td></tr>
                        <tr><td id="tdLabel"><b><font style="text-transform: capitalize">6. PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></td></tr>
                        <tr><td>
                                <table id ="dataTable6" border="0">
                                    <c:set var="i" value="1" />
                                    <c:set var="k" value="1" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandunganptg1}" var="line">
                                        <tr>
                                            <td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="6.${k}"/></td>
                                            <td valign="top">
                                                <s:textarea name="kandungan2${i}" id="kandungan2${i}"  rows="6" cols="150" class="normal_text">${line.kandungan}</s:textarea>
                                            </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                                </table></td></tr>
                        <tr>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable6',2)"/>&nbsp;
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:hidden name="rowCount4" value="${fn:length(actionBean.senaraiLaporanKandunganptg1)}" id="rowCount4"/>&nbsp;
                            </td>
                        </tr>
                    </c:if>
                </c:if>
                </table>

                <%-- <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MPJLT' }">--%>
                <c:if test="${actionBean.edit or actionBean.ptd}">
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                    </p>
                </c:if>
                <c:if test="${!actionBean.edit}">
                    <c:if test="${actionBean.ptg}">
                        <p align="center">
                            <s:button name="simpanPTG" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                        </p>
                    </c:if>
                </c:if>
                </table>
            </div>
        </fieldset>
    </div>
</s:form>