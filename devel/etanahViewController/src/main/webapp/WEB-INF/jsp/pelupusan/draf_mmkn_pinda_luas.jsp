<%--
    Document   : draf_mmkn_pinda_luas
    Created on : Mar 17, 2011, 10:05:21 AM
    Author     : Rohan
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
        font-size:13px;
        font-weight:bold;
    }

    #tdDisplay {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        width:14em;
    }
</style>
<script type="text/javascript">



     function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }


     function removeCommas(val){
        return val.replace(/\,/g,'');
    }


    function calc(){
        var cukai = $("#cukai").val();
        var premium = $("#premium").val();
               var jumlah = parseFloat(Number(removeCommas(cukai))) + parseFloat(Number(removeCommas(premium)));
        $("#jumlah").val(jumlah);
    }


    function addRow(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

        if(rowcount < 6){
            var row = table.insertRow(rowcount);

            var leftcell = row.insertCell(0);
            var el = document.createElement('textarea');
            el.name = 'huraianPentadbir' + rowcount;
            el.rows = 5;
            el.cols = 150;
            el.style.textTransform = 'uppercase';
            leftcell.appendChild(el);
        }
    <%--else{
        alert('Huraian Pentadbir Tanah telah lengkap.');
        $("#syorptd").focus();
        return true;
    }--%>

            }

            function addRow2(tableid){
                var table = document.getElementById(tableid);
                var rowcount = table.rows.length;

                if(rowcount < 6){
                    var row = table.insertRow(rowcount);

                    var leftcell = row.insertCell(0);
                    var el = document.createElement('textarea');
                    el.name = 'syorPentadbir' + rowcount;
                    el.rows = 5;
                    el.cols = 150;
                    el.style.textTransform = 'uppercase';
                    leftcell.appendChild(el);
                }
            }

            function addRow3(tableid){
                var table = document.getElementById(tableid);
                var rowcount = table.rows.length;

                if(rowcount < 6){
                    var row = table.insertRow(rowcount);

                    var leftcell = row.insertCell(0);
                    var e2 = document.createElement('textarea');
                    e2.name = 'huraianPtg' + rowcount;
                    e2.rows = 5;
                    e2.cols = 150;
                    e2.style.textTransform = 'uppercase';
                    leftcell.appendChild(e2);
    <%--} else{
        alert('Huraian Pentadbir Tanah telah lengkap.');
        $("#syorptd").focus();
        return true;--%>
                    }

                }

                function addRow4(tableid){
                    var table = document.getElementById(tableid);
                    var rowcount = table.rows.length;

                    if(rowcount < 6){
                        var row = table.insertRow(rowcount);

                        var leftcell = row.insertCell(0);
                        var e3 = document.createElement('textarea');
                        e3.name = 'syorPtg' + rowcount;
                        e3.rows = 5;
                        e3.cols = 150;
                        e3.style.textTransform = 'uppercase';
                        leftcell.appendChild(e3);
    <%-- } else{
         alert('Huraian Pentadbir Tanah telah lengkap.');
         $("#syorptd").focus();
         return true;--%>
                    }


                }
                ///aded

                function cariPopup(index){
                    // alert("search:"+index);
                    var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?search&index='+index ;
                    window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
                }

                function cariPopup2(index){
                    // alert("search:"+index);
                    var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?showFormCariKodSekatan&index='+index ;
                    window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
                }

                function test(){
                    var a = "hi" ;

                }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMKNPindaLuasActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.idKertas"/>
    <s:hidden name="edit" id="edit"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend> </legend>
            <p align="center">
                <b> MAJLIS MESYUARAT KERAJAAN NEGERI </b>
            </p>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="10">

                    <tr><td id="tdLabel" ><b><font style="text-transform: uppercase"> PERMOHONAN  UNTUK  MEMINDA KELUASAN DARIPADA  ${actionBean.hakmilikPermohonan.luasTerlibat} 
                                    ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}  KEPADA ${actionBean.noPt.luasDilulus} ${actionBean.noPt.kodUOM.nama}  DI BAWAH SEKSYEN 83(3) KANUN TANAH NEGARA, DI ${actionBean.hakmilikPermohonan.lokasi},
                                    ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama} UNTUK ${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama} (${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}).
                                    </font></b></td>
                    </tr>
                    <tr><td id="tdLabel" width="100%"><b>1. TUJUAN</b></td></tr>

                    <tr><td>
                        <table width="100%">
                                <tr><td valign="top">1.1 </td>
                                <td> ${actionBean.tujuan}</td></tr>
                        </table>
                        </td></tr>

                    </table>
                            <table border="0" width="80%" cellspacing="10">
                                <tr><td id="tdLabel"><b><font style="text-transform: uppercase">2. Huraian Pentadbir Tanah  Daerah</font></b></td></tr>
                                <tr><td>
                                        2.1 Majlis Mesyuarat Kerajaan Negeri yang bersidang pada ${permohonanKertas.tarikhSidang}&nbsp; melalui Kertas Mesyuarat No. ${permohonanKertas.nomborRujukan}&nbsp; telah meluluskan
                                        pemberimilikan tanah seluas ${actionBean.noPt.luasDilulus}&nbsp; ${actionBean.noPt.kodUOM.nama}&nbsp; di ${actionBean.hakmilikPermohonan.lokasi}, ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama} Daerah
                                        ${actionBean.permohonan.cawangan.daerah.nama}&nbsp; secara hakmilik tetap.</td></tr>

                                <tr><td> 2.2 Hakmilik Sementara tanah ini telah didaftar dan dikeluarkan di bawah ${actionBean.hakmilikSementara} ${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}.</td></tr>

                                <tr><td> 2.3 Permohonan Ukur pemberimilikan ini telah dikemukakan ke JUPEM dan pihak JUPEM telah mengukur hslus tanah ini. Setelah ukuran halus dibuat, didapati keluasan tanah ini ialah
                                        ${actionBean.noPt.luasDilulus}&nbsp; ${actionBean.noPt.kodUOM.nama}&nbsp; iaitu melebihi keluasan  Kelulusan asanya iaitu ${actionBean.hakmilikPermohonan.luasTerlibat}  ${actionBean.hakmilikPermohonan.kodUnitLuas.nama} melebihi ${actionBean.calculation}  .</td></tr>
                                
                                <tr><td>  2.4 Menurut Seksyen 83(3) Kanun Tanah Negara, lebihan keluasan yang "Substantial" hendakiah dirujuk kepada Pihak Berkuasa Negeri.</td></tr>
                                

                                  <tr><td id="tdLabel" ><b><font style="text-transform: uppercase">3. Syor Pentadbir Dearah</font></b></td></tr>
                                  <tr><td>
                                          3.1 Pentadbir Tanah Dearah  ${actionBean.permohonan.cawangan.daerah.nama}&nbsp; mengesyorkan supaya diluluskan tambahan keluasan
                                          pemberimilikan tanah ini dari ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama} kepada ${actionBean.noPt.luasDilulus}&nbsp; ${actionBean.noPt.kodUOM.nama}&nbsp; dan dikenakan bayaran tambahan seperti berikut:
                                      </td></tr>
                                   <table border="0" width="35%">
                                    <c:if test="${!actionBean.edit}">
                                  <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium Tambahan </font></td>
                                      <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                      <td><s:text name="premium" id="premium" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                                  </tr>

                                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Cukai Tanah Tambahan </font></td>
                                      <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                      <td><s:text name="cukaiBagiTahunYangPertama" id="cukai" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                                  </tr>

                                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jumlah</font></td>
                                      <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                      <td><s:text name="jumlah" id="jumlah" disabled="true" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                                  </tr>
                                    </c:if>
                                 
                                   <c:if test="${actionBean.edit}">
                                  <tr>
                                     <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium Tambahan </font></td>
                                     <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                     <td>${actionBean.premium}</td>
                                  </tr>

                                  <tr>
                                      <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Cukai Tanah Tambahan </font></td>
                                      <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                      <td>${actionBean.cukaiBagiTahunYangPertama}</td>
                                  </tr>

                                  <tr>
                                      <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Cukai Tanah Tambahan </font></td>
                                      <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                      <td>${actionBean.jumlah}</td>
                                  </tr>

                                  </c:if>

                                 
                                   </table>
                                 <table border="0" width="80%" cellspacing="10">
                                  <tr><td>
                                           3.2 Dikemukakan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri.
                                      </td></tr>
                            </table>

                 <c:if test="${actionBean.edit}">
                     
                    <table border="0" width="80%" id="tbl1">
                        <tr><td id="tdLabel"><b><font style="text-transform: uppercase">5. HURAIAN PENGARAH TANAH DAN GALIAN &nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="huraianPtg" rows="5" cols="150"/></td></tr>
                        <c:if test="${actionBean.huraianPtg2 ne null && actionBean.huraianPtg2 ne 'TIADA DATA.'}">
                            <tr><td><s:textarea name="huraianPtg2" rows="5" cols="150"/></td></tr>
                        </c:if>
                        <c:if test="${actionBean.huraianPtg3 ne null && actionBean.huraianPtg3 ne 'TIADA DATA.'}">
                            <tr><td><s:textarea name="huraianPtg3" rows="5" cols="150"/></td></tr>
                        </c:if>
                        <c:if test="${actionBean.huraianPtg4 ne null && actionBean.huraianPtg4 ne 'TIADA DATA.'}">
                            <tr><td><s:textarea name="huraianPtg4" rows="5" cols="150"/></td></tr>
                        </c:if>
                        <c:if test="${actionBean.huraianPtg5 ne null && actionBean.huraianPtg5 ne 'TIADA DATA.'}">
                            <tr><td><s:textarea name="huraianPtg5" rows="5" cols="150"/></td></tr>
                        </c:if>

                    </table>
                    <table border="0" width="83%">
                        <tr> <td>
                                <c:if test="${actionBean.btn}">
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow3('tbl1')"/>
                                </c:if></td></tr>
                    </table>

                    <table border="0" width="80%" id="syorPtgtbl">
                        <tr><td id="tdLabel"><b><font style="text-transform: uppercase">6. SYOR PENGARAH TANAH DAN GALIAN &nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="syorPtg" rows="5" cols="150"/></td></tr>
                        <c:if test="${actionBean.syorPtg2 ne null && actionBean.syorPtg2 ne 'TIADA DATA.'}">
                            <tr><td><s:textarea name="syorPtg2" rows="5" cols="150"/></td></tr>
                        </c:if>
                        <c:if test="${actionBean.syorPtg3 ne null && actionBean.syorPtg3 ne 'TIADA DATA.'}">
                            <tr><td><s:textarea name="syorPtg3" rows="5" cols="150"/></td></tr>
                        </c:if>
                        <c:if test="${actionBean.syorPtg4 ne null  && actionBean.syorPtg4 ne 'TIADA DATA.'}">
                            <tr><td><s:textarea name="syorPtg4" rows="5" cols="150"/></td></tr>
                        </c:if>
                        <c:if test="${actionBean.syorPtg5 ne null && actionBean.syorPtg5 ne 'TIADA DATA.'}">
                            <tr><td><s:textarea name="syorPtg5" rows="5" cols="150"/></td></tr>
                        </c:if>
                    </table>
                        
                    <table border="0" width="83%">
                        <tr><td>
                                <c:if test="${actionBean.btn}">
                                    <s:button  class="btn" value="Tambah" name="add" onclick="addRow4('syorPtgtbl')"/>&nbsp;
                                </c:if></td></tr>
                    </table>

                </c:if>
                    </table>
                <p align="center">
                    <s:button name="simpan1" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>

            </div>
        </fieldset>
    </div>
</s:form>