<%-- 
    Document   : draf_mmkn_rayuan_mlk
    Created on : Jun 6, 2011, 12:05:01 PM
    Author     : Akmal
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
    
    #test {
        text-transform: capitalize;
}
</style>
<script type="text/javascript">

    function addRow(tableid) {

        // alert(tableID);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "2." +(rowCount+3);

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
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }

    function cariPopup2(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?showFormCariKodSekatan';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }

    function test(){
        var a = "hi" ;

    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMKN_RayuanActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.idKertas"/>
    <s:hidden name="edit" id="edit"/>

    <div class="subtitle" style="text-transform: capitalize">
        <fieldset class="aras1">
            <legend> </legend>
            <p align="center">
                <b> (MAJLIS MESYUARAT KERAJAAN NEGERI)</b>
            </p>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="10%">

               

                        <tr><td id="tdLabel" ><b><font style="text-transform: capitalize">
                                        <tr><td>
                                                <c:if test="${!actionBean.edit}">
                                                    <table width="100%">
                                                        <tr><td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                            <td> <s:textarea rows="6" cols="150" name="tajuk" class="normal_text" />
                                                            </td></tr>
                                                    </table>
                                                </c:if>

                                                <c:if test="${actionBean.edit}">
                                                    <table width="100%">
                                                        <tr><td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                            <td>  ${actionBean.tajuk}</td></tr>
                                                    </table>
                                                </c:if>
                                            </td></tr></font></b></td></tr>

                    


                        <tr><td>&nbsp;</td></tr>
                        <tr ><td id="tdLabel" width="100%"><b>1. TUJUAN</b></td></tr>
                    
                        <c:if test="${!actionBean.edit}">
                            <tr><td>
                                    <table width="100%">
                                        <tr><td valign="top">1.1 </td>
                                            <td> <s:textarea rows="6" cols="150" name="tujuan" class="normal_text"/></td></tr>
                                    </table>

                                </td></tr>
                            </c:if>
                            <c:if test="${actionBean.edit}">
                            <tr><td>
                                    <table width="100%">
                                        <tr><td valign="top">1.1 </td>
                                            <td>&nbsp;${actionBean.tujuan}</td></tr>
                                    </table>
                                </td></tr>
                            </c:if>


                    

                    <%--<tr>
                        <td>  1.2 Tanah yang dipohon adalah seperti ditunjuk dengan garis sempadan warna merah dalam pelan yang berkembar.</td>
                        </tr>--%>


                    <tr><td>&nbsp;</td></tr>
                    <tr><td>
                            <table border="0" width="100%" id="tblhuraian" cellspacing="0">
                                <tr><td id="tdLabel"><b><font style="text-transform: capitalize">2. LATAR BELAKANG</font></b></td></tr>
                                <tr><td>&nbsp;</td></tr>

                                
                                
                                    <tr><td>&nbsp;</td></tr>
                                    <c:if test="${!actionBean.edit}">
                                        <tr><td><table width="100%">
                                                <tr><td valign="top">2.1</td>
                                                    <td> <s:textarea rows="6" cols="150" name="perihalpermohonan" class="normal_text" /></td></tr>
                                            </table></td></tr></table>
                                        
                                            </c:if>

                                    <c:if test="${actionBean.edit}">
                                        <tr><td><table width="100%">
                                                <tr><td valign="top">2.1</td>
                                                    <td> ${actionBean.perihalpermohonan}</td></tr>
                                            </table></td></tr>
                                            </c:if>

                                
                                <c:if test="${!actionBean.edit}">
                                    <tr><td><table width="100%">
                                                <tr><td valign="top">2.2 </td>
                                                    <td> <s:textarea rows="6" cols="150" name="perihalpermohonan1" class="normal_text" /></td></tr>
                                            </table></td></tr>
                                        </c:if>

                            <c:if test="${actionBean.edit}">
                        <tr><td><table width="100%">
                                    <tr><td valign="top">2.2</td>
                                        <td>&nbsp;${actionBean.perihalpermohonan1}</td></tr>
                                </table></td></tr>
                            </c:if>

                    <tr><td>
                            <c:if test="${!actionBean.edit}">
                                <table id ="dataTable1" border="0">
                                    <c:set var="i" value="3" />
                                    <c:set var="k" value="3" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line" begin="2">
                                        <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="2.1.${k}"/></td>
                                            <td valign="top"><font style="text-transform: capitalize"><s:textarea name="kandungan1${i}" id="kandungan1${i}"  rows="6" cols="150" class="normal_text">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                </table></td></tr>
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable1',3)"/>&nbsp;</td>
                        </tr>
                        <tr><td><s:hidden name="rowCount3" value="${fn:length(actionBean.senaraiLaporanKandungan1)}" id="rowCount3"/>&nbsp;</td></tr>
                    </c:if>

                    <tr><td>
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

                    <tr><td id="tdLabel"><b>3. RAYUAN</b></td></tr>
                    <tr><td><table width="100%" border="0">
                                <c:if test="${!actionBean.edit}">
                                    <tr><td valign="top">3.1 </td>
                                        <td> <s:textarea rows="6" cols="150" name="perihaltanah1" class="normal_text" /></td></tr>
                                    </c:if>

                                <c:if test="${actionBean.edit}">
                                    <tr><td>3.1.1</td>
                                        <td>${actionBean.perihaltanah1}</td></tr>
                                    </c:if>
                            </table></td></tr>

                   
                    <tr><td>&nbsp;</td></tr>
                    <tr><td id="tdLabel"><b><font style="text-transform: capitalize">5. PERAKUAN PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}&nbsp;</font></b></td></tr>
                    <tr><td><table width="100%">
                                <c:if test="${!actionBean.edit}">
                                    <tr><td valign="top">5.1 </td>
                                        <td> <s:textarea rows="6" cols="150" name="pentabirtanah1" class="normal_text" /></td></tr>
                                    </c:if>

                                <c:if test="${actionBean.edit}">
                                    <tr><td valign="top">5.1 </td>
                                        <td>&nbsp;${actionBean.pentabirtanah1}</td></tr>
                                    </c:if>

                            </table></td></tr>


                    <tr><td>
                            <c:if test="${!actionBean.edit}">
                                <table id ="dataTable5" border="0">
                                    <c:set var="i" value="2" />
                                    <c:set var="k" value="2" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandunganpbtanah}" var="line">
                                        <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="5.1.${k}"/></td>
                                            <td valign="top"><font style="text-transform: capitalize"><s:textarea name="kandungan5${i}" id="kandungan5${i}"  rows="6" cols="150" class="normal_text">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                </table></td></tr>
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable5',2)"/>&nbsp;</td>
                        </tr>
                        <tr><td><s:hidden name="rowCount6" value="${fn:length(actionBean.senaraiLaporanKandunganpbtanah)}" id="rowCount6"/>&nbsp;</td></tr>

                    </c:if>

                    <tr><td>
                            <c:if test="${actionBean.edit}">
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

                    <tr><td><table width="100%">
                                <c:if test="${!actionBean.edit}">
                                    <tr><td valign="top">5.2 </td>
                                        <td> <s:textarea rows="6" cols="150" name="pentabirtanah2" class="normal_text" /></td></tr>
                                    </c:if>

                                <c:if test="${actionBean.edit}">
                                    <tr><td valign="top">5.2 </td>
                                        <td>&nbsp;${actionBean.pentabirtanah2}</td></tr>
                                    </c:if>

                            </table></td></tr>


                        <table border="0" width="80%" cellspacing="10" >
                            <%-- <tr>
                                <td id="tdDisplay">a)Jenis Hakmilik </td>
                                <td width="2%"><b> : </b></td>
                                <td>
                                    <c:if test="${!actionBean.edit}">
                                        <s:select name="kodHmlk" >
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:option value="GRN">Geran</s:option>
                                            <s:option value="HSM">Hakmilik Sementara (Mukim)</s:option>
                                            <s:option value="HSD">Hakmilik Sementara Daftar</s:option>
                                            <s:option value="PM">Pajakan Mukim</s:option>
                                            <s:option value="PN">Pajakan Negeri</s:option>
                                            
                                        </s:select>
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${actionBean.kodHmlk} -  ${actionBean.hakmilikPermohonan.kodHakmilik.nama}
                                    </c:if>
                                </td>
                            </tr>


                            <tr>
                                <td id="tdDisplay">b)Tempoh</td>
                                <td width="2%"><b> : </b></td>
                                <td>
                                    <c:if test="${!actionBean.edit}">
                                        <s:text id="tempoh" name="hakmilikPermohonan.tempohHakmilik" size="10"/> &nbsp; tahun
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${actionBean.hakmilikPermohonan.tempohHakmilik} &nbsp; tahun
                                    </c:if>
                                </td>
                            </tr> --%>


                            
                               <%-- <tr><td id="tdDisplay">d)Hasil </td>
                                <td width="2%"><b> : </b></td>
                                <c:if test="${!actionBean.edit}">
                                    <%--<td>RM<s:text name="hakmilikPermohonan.cukaiPerMeterPersegi" size="5"/>  bagi tiap-tiap 100 m.p atau Sebahagian daripadanya (RM <s:text name="hakmilikPermohonan.cukaiPerLot" size="5"/> bagi setiap hektar)</td>--%>
                                 <%--     <td><s:text name="hakmilikPermohonan.keteranganCukaiBaru" size="80" class="normal_text"/></td>
                                </c:if>
                                <c:if test="${actionBean.edit}">
                                    <%--<td> RM  ${actionBean.hakmilikPermohonan.cukaiPerMeterPersegi}  bagi tiap-tiap 100 m.p atau Sebahagian daripadanya (RM ${actionBean.hakmilikPermohonan.cukaiPerLot} bagi setiap hektar)</td>--%>
                               <%--       <td>${actionBean.hakmilikPermohonan.keteranganCukaiBaru}</td>
                                </c:if>

                            </tr>

                            <tr>
                                <td id="tdDisplay">e) Bayaran Ukur</td>
                                <td width="2%"><b> : </b></td>
                                <td> Mengikut P.U (A) 438</td>
                            </tr>
                            <tr>
                                <td id="tdDisplay">f) Penjenisan</td>
                                <td width="2%"><b> : </b></td>
                                <td> ${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</td>
                            </tr>
                            <tr>
                            <td width="20%" style="color: #003194; font-weight: 700">g) Syarat Nyata </td>
                            <td ><b> : </b></td>
                            <c:if test="${!actionBean.edit}"> <td>
                                    <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}--${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                                    <s:hidden name="kod" id="kod"/><br>
                                    <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/></td></c:if>
                                   <c:if test="${actionBean.edit}">
                                    <td> ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</td>
                                </c:if>
                            </tr>

       <tr>
           <c:if test="${!actionBean.edit}"><td width="20%" style="color: #003194; font-weight: 700">h) Sekatan Kepentingan </td>
           <td ><b> : </b></td>
           <td><s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod}--${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
               <s:hidden name="kodSktn" id="kodSktn"/><br>
               <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/></td></c:if>
                 <c:if test="${actionBean.edit}">
                                    <td>${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}</td>
                                    </c:if> 
       </tr>
                        </table>
             
                        <table border="0" width="100%" cellspacing="10">
                            <tr>

                                
         
                                <tr><td id="tdLabel">II. <u>Syarat Am</u></td></tr>

                                <tr>
                                    <td> - Tiada </td>
                                </tr>
                       
                            
                                <%-- <tr><td id="tdLabel">II. <u>Syarat Am</u></td></tr>

                                <tr>
                                    <td> Setelah hakmilik didaftarkan, pemilik tanah ini dikehendaki membuat permohonan untuk menjadikan hakmiliknya kepada Tanah Adat Melaka (MCL).</td>
                                </tr> --%>
                        
                        </table>

                        <c:if test="${actionBean.edit}">
                            <c:if test="${actionBean.ptg}">
                            <tr><td>&nbsp;</td></tr>
                            <tr><td id="tdLabel"><b><font style="text-transform: capitalize">6. PERAKUAN PENGARAH TANAH DAN GALIAN </font></b></td></tr>
                            <%--<tr><td><table width="100%">
                                            <tr><td valign="top">6.1 </td>
                                                <td> <s:textarea rows="6" cols="150" name="hurianpengarah" /></td></tr>
                                    </table></td></tr>--%>
                            <tr><td>
                                    <table id ="dataTable6" border="0">
                                        <c:set var="i" value="1" />
                                        <c:set var="k" value="1" />
                                        <c:forEach items="${actionBean.senaraiLaporanKandunganptg1}" var="line">
                                            <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="6.${k}"/></td>
                                                <td valign="top"><font style="text-transform: capitalize"><s:textarea name="kandungan2${i}" id="kandungan2${i}"  rows="6" cols="150" class="normal_text">${line.kandungan}</s:textarea></font>
                                                </td></tr>
                                                <c:set var="i" value="${i+1}" />
                                                <c:set var="k" value="${k+1}" />
                                            </c:forEach>
                                    </table></td></tr>
                            <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable6',2)"/>&nbsp;</td>
                            </tr>
                            <tr><td><s:hidden name="rowCount4" value="${fn:length(actionBean.senaraiLaporanKandunganptg1)}" id="rowCount4"/>&nbsp;</td></tr>
                        </c:if>
                        </c:if>

                    </table>

                    <%-- <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">--%>
                    <c:if test="${!actionBean.edit}">
                        <p align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.edit}">
                        <c:if test="${actionBean.ptg}"> 
                        <p align="center">
                            <s:button name="simpanPTG" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                        </c:if>
                    </c:if>


            </div>
        </fieldset>
    </div>
</s:form>