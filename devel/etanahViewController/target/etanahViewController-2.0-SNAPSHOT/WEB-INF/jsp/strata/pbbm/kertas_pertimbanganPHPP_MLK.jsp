<%--
    Document   : kertas_pertimbanganPHPP_MLK
    Created on : sept 16, 2012, 10:42:01 AM
    Author     : murali
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:150px;
        margin-right:0.5em;
        text-align:right;
        width:18em;
        vertical-align:top;
    }
    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }

</style>
<script type="text/javascript">

    function addRow1(tableid){
        $("#abc").hide();
        $("#defaultdT1").hide();
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 4." +(rowcount+1)+"</b>";
        cell0.style.verticalAlign="top";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'asaspertimbangan' + (rowcount+1);
        el.rows = 5;
        el.cols = 120;
        cell1.appendChild(el);
        document.getElementById("rowCount1").value=rowcount+1;

        var cell0 = row.insertCell(2);    
        var e1 = document.createElement("IMG");
        e1.src = '${pageContext.request.contextPath}/images/not_ok.gif';
        e1.setAttribute("alt" , "Klik Untuk Hapus");
        e1.id =(rowcount);
        e1.onclick=function(){deleteRow2('',(e1.id));};
        cell0.appendChild(e1);
    }

    function deleteRow2(idKandungan,index)
    {
        document.getElementById('dataTable1').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount1").value;
            document.getElementById("rowCount1").value = rowCount-1;
            if(rowCount == 1){
                $("#defaultdT1").show();
            }
            regenerateBill3('dataTable1');
        }else{
            var url = '${pageContext.request.contextPath}/strata/kertasMLKPHPP?deleteSingleSemakKertasptg&idKandungan='+idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function regenerateBill3(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 0){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>4."+(i+1)+"</b>";
                    table.rows[i].cells[1].childNodes[0].name= 'asaspertimbangan'+(i+1);
                    table.rows[i].cells[2].childNodes[0].id= i;
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }

    function addRow2(tableid){

        $("#abc").hide();
        $("#defaultdT").hide();

        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 3." +(rowcount+1)+"</b>";
        cell0.style.verticalAlign="top";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'ulasan' + (rowcount+1);
        el.rows = 5;
        el.cols = 120;
        leftcell.appendChild(el);
        document.getElementById("rowCount2").value=rowcount+1;

        var cell0 = row.insertCell(2);
        var e1 = document.createElement("IMG");
        e1.src = '${pageContext.request.contextPath}/images/not_ok.gif';
        e1.setAttribute("alt" , "Klik Untuk Hapus");

        e1.id =(rowcount);
        e1.onclick=function(){deleteRow1('',(e1.id));};
        cell0.appendChild(e1);
    }
    function deleteRow1(idKandungan,index)
    {
        document.getElementById('dataTable2').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount2").value;
            document.getElementById("rowCount2").value = rowCount-1;
            if(rowCount == 1){
                $("#defaultdT").show();
            }
            regenerateBill2('dataTable2');
        }else{
            var url = '${pageContext.request.contextPath}/strata/kertasMLKPHPP?deleteSingle&idKandungan='+idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function regenerateBill2(tableid){
        try{

            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 0){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>3."+(i+1)+"</b>";
                    table.rows[i].cells[1].childNodes[0].name= 'ulasan'+(i+1);
                    table.rows[i].cells[2].childNodes[0].id= i;
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }

    function addRow3(tableid){
        $("#abc").hide();
        $("#defaultdT2").hide();
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 5." +(rowcount+1)+"</b>";
        cell0.style.verticalAlign="top";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'syorTP' + (rowcount+1);
        el.rows = 5;
        el.cols = 120;
        cell1.appendChild(el);
        document.getElementById("rowCount3").value=rowcount+1;

        var cell0 = row.insertCell(2);
        var e1 = document.createElement("IMG");
        e1.src = '${pageContext.request.contextPath}/images/not_ok.gif';
        e1.setAttribute("alt" , "Klik Untuk Hapus");
        e1.id =(rowcount);
        e1.onclick=function(){deleteRow3('',(e1.id));};
        cell0.appendChild(e1);
    }

    function deleteRow3(idKandungan,index){
        document.getElementById('dataTable3').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount3").value;
            document.getElementById("rowCount3").value = rowCount-1;
            if(rowCount == 1){
                $("#defaultdT2").show();
            }
            regenerateBill4('dataTable3');
        }else{
            var url = '${pageContext.request.contextPath}/strata/kertasMLKPHPP?deleteSingleSemakKertasptg&idKandungan='+idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    function regenerateBill4(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 0){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>5."+(i+1)+"</b>";
                    table.rows[i].cells[1].childNodes[0].name= 'syorTP'+(i+1);
                    table.rows[i].cells[2].childNodes[0].id= i;
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }
    $(document).ready(function(){
        $('#abc').show();
    });

    function hakdetails (val) {
        var stageId = '${actionBean.stageId}';
        doBlockUI();
        if(stageId == 'sediakertasptg'){
            var tujuan = $("#tujuan").val();
            var rowCount2 = $("#rowCount2").val();
            var url = '${pageContext.request.contextPath}/strata/kertasMLKPHPP?hakdetails&idMh='+val+'&tujuan='+tujuan+'&rowCount2='+rowCount2;
        }
        if(stageId == 'semakkertasptg' || stageId == 'perakuan'){
            var rowCount1 = $("#rowCount1").val();
            var rowCount3 = $("#rowCount3").val();
            var url = '${pageContext.request.contextPath}/strata/kertasMLKPHPP?hakdetails&idMh='+val+'&rowCount1='+rowCount1+'&rowCount3='+rowCount3;
        }
        if(stageId == 'keputusan1'){
            var url = '${pageContext.request.contextPath}/strata/kertasMLKPHPP?hakdetails&idMh='+val;
        }
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){                
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
             
    }
    
</script>
<s:form beanclass="etanah.view.strata.KertasPertimbanganMLKPHPPActionBean">
    <div id="abc">
        <s:messages/>
        <s:errors/>

    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%" id="sa">                    
                    <tr><td align="center"><b>(KERTAS PERTIMBANGAN <font style="text-transform: uppercase">${actionBean.permohonan.kodUrusan.nama})</font></b></td></tr>
                    <tr><td><b>1. TUJUAN</b><br>
                        </td></tr>
                    <tr><td width="100%" id="tdDisplay">
                            <c:if test="${edit3}">                                
                                <s:textarea name="tujuan" id="tujuan" rows="5" cols="150" class="normal_text">${actionBean.tujuan}</s:textarea>
                            </c:if>
                            <c:if test="${!edit3}">
                                <s:textarea name="tujuan" id="tujuan" readonly="true" rows="5" cols="150" class="normal_text"/>
                            </c:if>
                        </td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td><b>2. RINGKASAN PERMOHONAN</b></td></tr>
                    <tr><td>

                            <display:table class="tablecloth" name="${actionBean.listHakmilikPihak}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column  title="Nama Pemohon" class="nama">
                                ${line.nama}
                            </display:column>
                            <display:column property="noPengenalan" title="Nombor Pengenalan" />
                            <display:column title="Alamat" >${line.alamat1}
                                <c:if test="${line.alamat2 ne null}"> , </c:if>
                                ${line.alamat2}
                                <c:if test="${line.alamat3 ne null}"> , </c:if>
                                ${line.alamat3}
                                <c:if test="${line.alamat4 ne null}"> , </c:if>
                                ${line.alamat4}</display:column>
                            <display:column property="poskod" title="Poskod" />
                            <display:column property="negeri.nama" title="Negeri" />

                        </display:table>
                                <p>
                                <p>
                                <p>
                                <p>
                            <table border="0" width="100%" id="sa">                                
                                <tr>
                                    <td id="tdLabel">Senarai Hakmilik Terlibat :</td>
                                    <td id="tdDisplay">
                                        <s:select name="idMh" onchange="hakdetails(this.value);" id="idMh">
                                            <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                                                <s:option value="${item.id}" style="width:400px">
                                                    ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                                                </s:option>
                                            </c:forEach>
                                        </s:select>
                                    </td>
                                </tr>

                                <%--<tr>
                                    <td id="tdLabel">Nama Pemohon :</td>
                                    <td id="tdDisplay"> ${actionBean.pihak.nama} </td>
                                </tr>
                                <tr>
                                    <td id="tdLabel">Alamat :</td>
                                    <td id="tdDisplay">${actionBean.pihak.alamat1} </td>
                                </tr>
                                <tr>
                                    <td id="tdLabel">&nbsp;</td>
                                    <td id="tdDisplay">${actionBean.pihak.alamat2} ${actionBean.pihak.alamat3} </td>
                                </tr>
                                <tr>
                                    <c:if test="${actionBean.pihak.alamat4 ne null}">
                                        <td id="tdLabel">&nbsp;</td>
                                        <td id="tdDisplay">${actionBean.pihak.alamat4} </td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td id="tdLabel">&nbsp;</td>
                                    <td id="tdDisplay">  ${actionBean.pihak.poskod} <font style="text-transform: uppercase">${actionBean.pihak.negeri.nama}</font></td>
                                </tr>--%>
                                <tr><td id="tdLabel">Nombor Hakmilik :</td>
                                    <td id="tdDisplay"> <c:if test="${actionBean.nHakmilik ne null}">    ${actionBean.nHakmilik}&nbsp; </c:if>
                                        <c:if test="${actionBean.nHakmilik eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Lot/PT :</td>
                                    <td id="tdDisplay"> <c:if test="${actionBean.nLot ne null}">  ${actionBean.nLot}&nbsp;</c:if>
                                        <c:if test="${actionBean.nLot eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Jenis Kegunan Tanah :</td>
                                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}">  ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Syarat Nyata :</td>
                                    <td id="tdDisplay">  <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}"> ${actionBean.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Sekatan Kepentingan :</td>
                                    <td id="tdDisplay">   <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Jenis Bangunan :</td>
                                    <td id="tdDisplay">
                                        <c:choose>
                                            <c:when test="${actionBean.mohonbngn.kekal eq 'Y'}"> Kekal</c:when>
                                            <c:when test="${actionBean.mohonbngn.kekal eq 'N'}"> Sementara</c:when>
                                            <c:otherwise>Tiada Data</c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Nombor Bangunan :</td>
                                    <td id="tdDisplay">   <c:if test="${actionBean.hakmilik.noBangunan ne null}">${actionBean.hakmilik.noBangunan}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.noBangunan eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Nombor Tingkat :</td>
                                    <td id="tdDisplay">   <c:if test="${actionBean.hakmilik.noTingkat ne null}">${actionBean.hakmilik.noTingkat}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.noTingkat eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Nombor Petak :</td>
                                    <td id="tdDisplay">   <c:if test="${actionBean.hakmilik.noPetak ne null}">${actionBean.hakmilik.noPetak}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.noPetak eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Luas Petak :</td>
                                    <td id="tdDisplay">  <c:if test="${actionBean.hakmilik.luas ne null}"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</c:if>
                                        <c:if test="${actionBean.hakmilik.luas eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td id="tdLabel">Unit Syer Petak:</td>
                                    <td id="tdDisplay">  <c:if test="${actionBean.hakmilik.unitSyer ne null}">${actionBean.hakmilik.unitSyer}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.unitSyer eq null}"> 0 </c:if>
                                    </td>
                                </tr>
                            </table>
                        </td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td><b>3. ULASAN DARI JABATAN UKUR DAN PEMETAAN </b></td></tr>
                    <c:if test="${edit3}">
                        <tr><td>
                                <c:if test="${fn:length(actionBean.senaraiKandungan2)==0}">
                                    <table id="defaultdT">
                                        <tr><td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td>
                                                <s:textarea name="dummy" id="dummy"  rows="5" cols="120" class="normal_text" readonly="true">Tiada Ulasan. Sila klik butang Tambah untuk menambah ulasan.</s:textarea>
                                            </td></tr></table>
                                        </c:if>

                                <table id ="dataTable2">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan2}" var="line">
                                        <tr>
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td valign="top">
                                                <b>3.${i}</b>
                                            </td>
                                            <td>
                                                <s:textarea name="ulasan${i}" id="ulasan${i}"  rows="5" cols="120" class="normal_text">${line.kandungan}</s:textarea>
                                            </td>
                                            <td>
                                                <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif' id="${i-1}" onclick="deleteRow1(${line.idKandungan},${i-1})" />
                                            </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td><s:hidden name="rowCount2" id="rowCount2"/>
                                <s:hidden name="count2" id="count2" value="${fn:length(actionBean.senaraiKandungan2)}"/> </td>
                        </tr>
                        <tr>
                            <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow2('dataTable2')"/>&nbsp;
                            </td>
                        </tr>
                    </c:if>

                    <c:if test="${!edit3}">
                        <tr><td>
                                <table>
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan2}" var="line">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td valign="top"><b>3.${i}</b></td>
                                            <td><s:textarea name="ulasan${i}" id="ulasan${i}" readonly="true" rows="5" cols="120" class="normal_text">${line.kandungan}</s:textarea></td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.stageId ne 'sediakertasptg'}">                      

                        <tr><td>&nbsp;</td></tr>
                        <tr><td><b>4. ASAS-ASAS PERTIMBANGAN </b></td></tr>
                        <c:if test="${edit4}">
                            <tr><td>
                                    <c:if test="${fn:length(actionBean.senaraiKandungan1)==0}">
                                        <table id="defaultdT1">
                                            <tr><td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td>
                                                    <s:textarea name="dummy" id="dummy"  rows="5" cols="120" class="normal_text" readonly="true">Tiada rekod. Sila klik butang Tambah untuk menambah Asas-asas Pertimbangan.</s:textarea>
                                                </td></tr></table>
                                            </c:if>
                                    <table id ="dataTable1">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan1}" var="line">
                                            <tr>
                                                <td style="display:none">${line.idKandungan}</td>
                                                <td valign="top"><b>4.${i}</b></td>
                                                <td><s:textarea name="asaspertimbangan${i}" id="asaspertimbangan${i}"  rows="5" cols="120" class="normal_text">${line.kandungan}</s:textarea> </td>
                                                <td>
                                                    <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif' id="${i-1}" onclick="deleteRow2(${line.idKandungan},${i-1})" />
                                                </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr><td><s:hidden name="rowCount1" id="rowCount1"/>
                                    <s:hidden name="count1" id="count1" value="${fn:length(actionBean.senaraiKandungan1)}"/>
                                </td></tr>
                            <tr>
                                <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable1')"/>&nbsp;
                                </td>
                            </tr>
                        </c:if>

                        <c:if test="${!edit4}">
                            <tr><td>
                                    <table>
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan1}" var="line">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td valign="top"><b>4.${i}</b></td>
                                                <td><s:textarea name="ulasan${i}" id="ulasan${i}" readonly="true" rows="5" cols="120" class="normal_text">${line.kandungan}</s:textarea></td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>



                        <tr><td>&nbsp;</td></tr>
                        <tr><td><b>5. SYOR DAN PERAKUAN TIMBALAN PENDAFTAR HAKMILIK GERAN TANAH </b></td></tr>
                        <c:if test="${edit5}">
                            <tr><td>
                                    <c:if test="${fn:length(actionBean.senaraiKandungan3)==0}">
                                        <table id="defaultdT2">
                                            <tr><td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td>
                                                    <s:textarea name="dummy" id="dummy"  rows="5" cols="120" class="normal_text" readonly="true">Tiada rekod. Sila klik butang Tambah untuk menambah syor dan perakuan.</s:textarea>
                                                </td></tr></table>
                                            </c:if>

                                    <table id ="dataTable3">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                            <tr>
                                                <td style="display:none">${line.idKandungan}</td>
                                                <td valign="top"><b>5.${i}</b></td>
                                                <td><s:textarea name="syorTP${i}" id="syorTP${i}"  rows="5" cols="120" class="normal_text">${line.kandungan}</s:textarea> </td>
                                                <td>
                                                    <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif' id="${i-1}" onclick="deleteRow3(${line.idKandungan},${i-1})" />
                                                </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr><td><s:hidden name="rowCount3" id="rowCount3"/>
                                    <s:hidden name="count3" id="count3" value="${fn:length(actionBean.senaraiKandungan3)}"/>
                                </td></tr>
                            <tr>
                                <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable3')"/>&nbsp;
                                </td>
                            </tr>
                        </c:if>


                        <c:if test="${!edit5}">
                            <tr><td>
                                    <table>
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td valign="top"><b>5.${i}</b></td>
                                                <td><s:textarea name="ulasan${i}" id="ulasan${i}" readonly="true" rows="5" cols="120" class="normal_text">${line.kandungan}</s:textarea></td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>

                    <tr><td>&nbsp;</td></tr>
                    <tr align="center"><td>
                            <c:if test="${edit}">
                                <c:if test="${!(edit4 && edit5)}">
                                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                </c:if>

                                <c:if test="${(edit4 && edit5)}">
                                    <s:button name="simpanSemakKertasptg" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                </c:if>
                            </c:if>
                        </td></tr>
                </table>
            </div>

        </fieldset>
    </div>
</s:form>