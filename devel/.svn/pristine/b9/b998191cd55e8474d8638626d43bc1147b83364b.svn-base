<%-- 
    Document   : kertas_rayuan_jkbb
    Created on : Mar 8, 2011, 2:20:23 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<%--<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
--%>

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
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;        
    }
</style>
<script type="text/javascript">
    $(document).ready(function() {       
        var value = $('#bilrayuan').val();
    <%--alert("value:"+value.toLowerCase());--%>
            if(value.toLowerCase() == "pertama")
            {
                $('#rayuan').hide();
            }else{
    <%--alert("show");--%>
                $('#rayuan').show();
            }

        });

        function changeRayuan1(value){
    <%--alert("changeRayuan value:"+value);--%>
            if(value.toLowerCase() == "pertama")
            {
                $('#rayuan').hide();
            }else{
                $('#rayuan').show();
            }
        }

        function deleteRow(tableid,recordCount){

            var table = document.getElementById(tableid);        
            var rowIndex = document.getElementById(recordCount).value;        
    <%--alert("rowIndex:"+rowIndex);--%>
            if(rowIndex > 0){
                document.getElementById(recordCount).value = parseInt(document.getElementById(recordCount).value)-1;
                document.getElementById(tableid).deleteRow((parseInt(rowIndex)));
                var testCount = document.getElementById("testCount").value;
                if(parseInt(rowIndex) <= parseInt(testCount)){

    <%--alert("javacall");--%>
                    try{            
                        var url = '${pageContext.request.contextPath}/pembangunan/rayuanjkbb?deleteSingle';
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');

                    }catch(e){
                        alert(e);
                    }
                }
            }else{
                alert("Tiada Boleh Dihapuskan");
                return false;
            }
        }


        function deleteRow2(tableid,recordCount){

            var table = document.getElementById(tableid);
            var rowIndex = document.getElementById(recordCount).value;
    <%--alert("rowIndex:"+rowIndex);--%>
            if(rowIndex > 0){
                document.getElementById(recordCount).value = parseInt(document.getElementById(recordCount).value)-1;
                document.getElementById(tableid).deleteRow((parseInt(rowIndex)));
                var testCount = document.getElementById("testCount2").value;
                if(parseInt(rowIndex) <= parseInt(testCount)){

    <%--alert("javacall");--%>
                    try{
                        var url = '${pageContext.request.contextPath}/pembangunan/rayuanjkbb?deleteSingle2';
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');

                    }catch(e){
                        alert(e);
                    }
                }
            }else{
                alert("Tiada Boleh Dihapuskan");
                return false;
            }
        }

        function addRow(tableId,countId){
            var table = document.getElementById (tableId);
            var rowcount = table.rows.length;
            var row = table.insertRow(rowcount);
            document.getElementById("count").value =rowcount;
    <%--alert("Row Count:"+rowcount);--%>
        
            var cell1 = row.insertCell(0);
            var bil = document.createTextNode(rowcount);
            cell1.appendChild(bil);

            var cell2 = row.insertCell(1);
            var e2 = document.createElement("INPUT");
            e2.setAttribute("type","text");
            e2.setAttribute("name","pembangunan"+(rowcount-1));
            e2.setAttribute("id","pembangunan" +(rowcount-1));
            e2.setAttribute("size","35");
            cell2.appendChild(e2);

            var cell3 = row.insertCell(2);
            var e3 = document.createElement("INPUT");
            e3.setAttribute("type","text");
            e3.setAttribute("name","unit"+(rowcount-1));
            e3.setAttribute("id","unit" +(rowcount-1));
            e3.setAttribute("size","20");
            cell3.appendChild(e3);

            var cell4 = row.insertCell(3);
            var e4 = document.createElement("INPUT");
            e4.setAttribute("type","text");
            e4.setAttribute("name","luas"+(rowcount-1));
            e4.setAttribute("id","luas" +(rowcount-1));
            e4.setAttribute("size","20");
            cell4.appendChild(e4);

            var cell5 = row.insertCell(4);
            var e5 = document.createElement("INPUT");
            e5.setAttribute("type","text");
            e5.setAttribute("name","hargaUnit"+(rowcount-1));
            e5.setAttribute("id","hargaUnit" +(rowcount-1));
            e5.setAttribute("size","35");
            cell5.appendChild(e5);
        }

        function addRow2(tableId,countId){

            var table = document.getElementById (tableId);
            var rowcount = table.rows.length;
            var row = table.insertRow(rowcount);
            document.getElementById("count2").value =rowcount;
    <%--alert("Row Count2:"+rowcount);--%>
        
            var cell1 = row.insertCell(0);
            var bil = document.createTextNode(rowcount);
            cell1.appendChild(bil);
            var cell2 = row.insertCell(1);
            var e2 = document.createElement("INPUT");
            e2.setAttribute("type","text");
            e2.setAttribute("name","tarikhRayuan"+(rowcount-1));
            e2.setAttribute("id","tarikhRayuan" +(rowcount-1));
            e2.setAttribute("size","20");
    <%--e2.className ="datepicker"+rowcount;--%>
            e2.onclick = function(){setDatepicker(rowcount-1);};
            e2.onmouseover = function(){setDatepicker(rowcount-1);};
            cell2.appendChild(e2);

            var cell3 = row.insertCell(2);
    <%--var e3 = document.createElement("INPUT");
    e3.setAttribute("type","textarea");--%>
            var e3 = document.createElement('textarea');
            e3.rows=6;
            e3.cols=40;
            e3.setAttribute("name","jenisRayuan"+(rowcount-1));
            e3.setAttribute("id","jenisRayuan" +(rowcount-1));        
            cell3.appendChild(e3);

            var cell4 = row.insertCell(3);
    <%--var e4 = document.createElement("INPUT");
    e4.setAttribute("type","textarea");--%>
            var e4 = document.createElement('textarea');
            e4.rows=6;
            e4.cols=40;
            e4.setAttribute("name","kpsn"+(rowcount-1));
            e4.setAttribute("id","kpsn" +(rowcount-1));        
            cell4.appendChild(e4);

        }

        function removeKertasButiran(idKand){
            if(confirm('Adakah anda  pasti?')) {
    <%--alert("idKand:"+idKand);--%>
                var url = '${pageContext.request.contextPath}/pembangunan/rayuanjkbb?deletePermohonanKertasButiran&idKand='+idKand;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function removeKertasKeputusan(idKand){
            if(confirm('Adakah anda  pasti?')) {
    <%--alert("idKand:"+idKand);--%>
                var url = '${pageContext.request.contextPath}/pembangunan/rayuanjkbb?deletePermohonanKertasKeputusan&idKand='+idKand;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function setDatepicker(rowcount){
    <%--alert("rowcount:"+rowcount);--%>
            $("#tarikhRayuan"+(rowcount)).datepicker();
        }


        function validation(event, f){
            $('#dialog-confirm').dialog('open')
            $(function() {
                $( "#dialog-confirm" ).dialog({
                    resizable: false,
                    height:140,
                    modal: true,
                    buttons: {
                        "Tidak": function() {
                            //  alert('tidak selected');
                            $( this ).dialog( "close" );

                            return false;
                        },
                        "Ya": function() {
                            // alert('ya selected');
                            $( this ).dialog( "close" );

                            var q = $(f).formSerialize();
                            var url = f.action + '?' + event;
                            $.post(url,q,
                            function(data){
                                $('#page_div',this.document).html(data);
                            },'html');

                            return true;
                        }
                    }
                });
            });

        }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pembangunan.RayuanJKBBActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.kertas.idKertas"/>
    <div id="dialog-confirm" title="Kertas JKBB" style="display:none">
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
            <font size="3"> Adakah anda pasti untuk menjana Kertas Rayuan JKBB baru?</font>
        </p>
    </div>
    <%--<s:hidden name="kandunganK.kertas.idKertas"/>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <c:set scope="request" value="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik"/>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="10">
                    <tr><td align="center"><b>MESYUARAT JAWATANKUASA PECAH SEMPADAN DAN BELAH BAHAGI NEGERI MELAKA</b></td></tr>
                    <tr><td align="center"><b>RAYUAN</b> &nbsp; <s:text name="bilrayuan" id="bilrayuan" size="18" onkeyup="changeRayuan1(this.value);"/></td></tr>

                    <tr><td>
                            <table border="0">
                                <tr><td><b id="idLabel">PERSIDANGAN</b></td>
                                    <td><b id="idLabel"> : </b></td>
                                    <c:if test="${actionBean.stageId eq 'cetakrekodkpsnrayuanjkbb'}">
                                        <td><s:text name="persidangan" size="20"/></td>
                                    </c:if>
                                    <c:if test="${actionBean.stageId ne 'cetakrekodkpsnrayuanjkbb'}">
                                        <td id="idDisplay">${actionBean.persidangan} &nbsp;</td>
                                    </c:if>
                                </tr>
                                <tr><td id="idLabel"><b>MASA</b></td>
                                    <td><b id="idLabel"> : </b></td>
                                    <c:if test="${actionBean.stageId eq 'cetakrekodkpsnrayuanjkbb'}">
                                        <td>
                                            <%--<s:text name="masasidang" size="20" />
                                            <font color="red"><b>(HH:MM AM/PM)</b></font>--%>
                                            <s:select name="jam" style="width:60px;" id="jam">
                                                <s:option value="0">Jam</s:option>
                                                <c:forEach var="i" begin="1" end="12" step="1" varStatus ="status">
                                                    <c:if test="${i lt 10}">
                                                        <s:option value="0${i}">0${i}</s:option>
                                                    </c:if>
                                                    <c:if test="${i gt 9}">
                                                        <s:option value="${i}">${i}</s:option>
                                                    </c:if>
                                                </c:forEach>
                                            </s:select>
                                            <s:select name="minit" style="width:70px;" id="minit">
                                                <s:option value="0">Minit</s:option>
                                                <c:forEach var="i" begin="0" end="59" step="5" varStatus ="status">
                                                    <c:if test="${i lt 10}">
                                                        <s:option value="0${i}">0${i}</s:option>
                                                    </c:if>
                                                    <c:if test="${i gt 9}">
                                                        <s:option value="${i}">${i}</s:option>
                                                    </c:if>
                                                </c:forEach>
                                            </s:select>
                                            <s:select name="pagiPetang" style="width:80px;" id="masa">
                                                <s:option value="0">Pilih</s:option>
                                                <s:option value="AM">Pagi</s:option>
                                                <s:option value="PM">Petang</s:option>
                                            </s:select>
                                        </td>
                                    </c:if>
                                    <c:if test="${actionBean.stageId ne 'cetakrekodkpsnrayuanjkbb'}">
                                        <td id="idDisplay">${actionBean.masasidang} &nbsp;</td>
                                    </c:if>
                                </tr>
                                <tr><td id="idLabel"><b>TARIKH</b></td>
                                    <td><b id="idLabel"> : </b></td>
                                    <c:if test="${actionBean.stageId eq 'cetakrekodkpsnrayuanjkbb'}">
                                        <td><s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /></td>
                                    </c:if>
                                    <c:if test="${actionBean.stageId ne 'cetakrekodkpsnrayuanjkbb'}">
                                        <td id="idDisplay">${actionBean.tarikhMesyuarat} &nbsp;</td>
                                    </c:if>
                                </tr>
                                <tr><td id="idLabel" valign="top"><b>TEMPAT</b></td>
                                    <td valign="top"><b id="idLabel"> : </b></td>
                                    <c:if test="${actionBean.stageId eq 'cetakrekodkpsnrayuanjkbb'}">
                                        <td><s:textarea name="tempatsidang" cols="50"/></td>
                                    </c:if>
                                    <c:if test="${actionBean.stageId ne 'cetakrekodkpsnrayuanjkbb'}">
                                        <td id="idDisplay">${actionBean.tempatsidang} &nbsp;</td>
                                    </c:if>
                                </tr>
                            </table>
                        </td></tr>                        
                    <tr><td><b>1. JENIS RAYUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="jenisrayuan" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.jenisrayuan} &nbsp;</td></tr>
                    </c:if>
                    <tr><td><b>2. JENIS PERMOHONAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="jenispermohonan" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.jenispermohonan} &nbsp;</td></tr>
                    </c:if>
                    <tr><td><b>3. BUTIR-BUTIR HAKMILIK </b></td></tr>
                    <tr><td>
                            <table border="0" width="122%">
                                <c:if test="${actionBean.saizList eq 1}">
                                    <tr>
                                        <td id="tdLabel"><font color="black">No.Lot/PT :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.lot.nama}&nbsp; <fmt:formatNumber  pattern="00" value="${actionBean.hakmilikSingle.noLot}"/></td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Jenis dan No. Hakmilik :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.kodHakmilik.kod}&nbsp; <fmt:formatNumber  pattern="00" value="${actionBean.hakmilikSingle.noHakmilik}"/>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Tempoh Pegangan :</font></td>
                                        <td id="tdDisplay">
                                            <c:if test = "${actionBean.hakmilikSingle.tempohPegangan ne null}">
                                                ${actionBean.hakmilikSingle.tempohPegangan}</td>
                                            </c:if>
                                            <c:if test = "${actionBean.hakmilikSingle.tempohPegangan eq null}">
                                            &nbsp;&nbsp; - </td>
                                        </c:if>

                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Bandar/Pekan/Mukim :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.bandarPekanMukim.nama} &nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Luas :</font></td>
                                        <td id="tdDisplay"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilikSingle.luas}"/>&nbsp; ${actionBean.hakmilikSingle.kodUnitLuas.nama}</td>
                                    </tr>                               
                                    <tr>
                                        <td id="tdLabel"><font color="black">Tuan Tanah :</font></td>                              
                                        <td>
                                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList[0].hakmilik.senaraiPihakBerkepentingan}" cellpadding="0" cellspacing="0" id="tbl2">
                                                <display:column title="Nama" style="vertical-align:baseline">
                                                    <c:if test="${tbl2.jenis.kod eq 'PM'}">
                                                        ${tbl2.pihak.nama}
                                                    </c:if>
                                                </display:column>
                                                <display:column title="Bahagian Dimiliki" style="vertical-align:baseline">
                                                    <c:if test="${tbl2.jenis.kod eq 'PM'}">
                                                        ${tbl2.syerPembilang}/${tbl2.syerPenyebut}
                                                    </c:if>
                                                </display:column>
                                            </display:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel" valign="top"><font color="black">Jenis Kegunaan Tanah :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.kegunaanTanah.nama}</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel" valign="top"><font color="black">Syarat Nyata :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.syaratNyata.syarat}</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel" valign="top"><font color="black">Sekatan Kepentingan :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.sekatanKepentingan.sekatan}</td>
                                    </tr>
                                </c:if>
                                <c:if test="${actionBean.saizList > 1}">
                                    <tr>
                                        <td id="tdLabel"><font color="black">No.Lot/PT :</font></td>
                                        <td id="tdDisplay">Sila Rujuk Lampiran</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Jenis dan No. Hakmilik :</font></td>
                                        <td id="tdDisplay">Sila Rujuk Lampiran</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Tempoh Pegangan :</font></td>
                                        <td id="tdDisplay">Sila Rujuk Lampiran</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Bandar/Pekan/Mukim :</font></td>
                                        <td id="tdDisplay">Sila Rujuk Lampiran</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Luas :</font></td>
                                        <td id="tdDisplay">Sila Rujuk Lampiran</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Tuan Tanah :</font></td>
                                        <td id="tdDisplay">Sila Rujuk Lampiran</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Jenis Kegunaan Tanah :</font></td>
                                        <td id="tdDisplay">Sila Rujuk Lampiran</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Syarat Nyata :</font></td>
                                        <td id="tdDisplay">Sila Rujuk Lampiran</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Sekatan Kepentingan :</font></td>
                                        <td id="tdDisplay">Sila Rujuk Lampiran</td>
                                    </tr>
                                </c:if>
                            </table>
                        </td></tr>
                    <tr><td><b>4. LOKASI TANAH</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea rows="5" cols="150" name="lokasitanah" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.lokasitanah} &nbsp;</td></tr>
                    </c:if>
                    <tr><td><b>5. TARIKH JKBB TERAWAL</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="tarikhjkbbawl" size="12" id="datepicker" class="datepicker"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhjkbbawl}"/>&nbsp;</td></tr>
                    </c:if>

                    <c:set scope="request" value="${actionBean.uniquePemohonList2}" var="senaraiPihak"/>    
                    <c:set scope="request" value="${actionBean.listPengarah}" var="senaraiPengarah"/>
                    <tr><td><b>6. PERIHAL PEMOHON : </b></td></tr>
                    <tr><td>
                            <table border="0" width="60%">
                                <tr>
                                    <td  id="tdLabel"><font color="black">Pemohon :</font></td>
                                    <td  id="tdDisplay">
                                        <table border="0" width="80%">
                                            <c:set value="1" var="i"/>
                                            <c:forEach items="${senaraiPihak}" var="pihak" end="0">
                                                <tr><td><font size="-1">                                                                
                                                            <c:out value="${pihak.nama}"/> 
                                                            <c:if test="${fn:length(senaraiPihak) > 1}">
                                                                dan ${fn:length(senaraiPihak)-1} yang lain
                                                            </c:if>                                                               
                                                        </font></td>
                                                </tr>
                                                <c:set value="${i + 1}" var="i"/>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td  id="tdLabel"><font color="black">Alamat :</font></td>
                                    <td  id="tdDisplay">
                                        <table border="0" width="80%">
                                            <c:set value="1" var="i"/>
                                            <c:forEach items="${senaraiPihak}" var="pihak" end="0" >
                                                <c:if test="${pihak.alamat1 ne null}">
                                                    <tr><td><font size="-1"><c:out value="${pihak.alamat1}"/></font></td></tr>
                                                </c:if>                                                  
                                                <c:if test="${pihak.alamat2 ne null}">
                                                    <tr><td><font size="-1"><c:out value="${pihak.alamat2}"/></font></td></tr>
                                                </c:if>
                                                <c:if test="${pihak.alamat3 ne null}">
                                                    <tr><td><font size="-1"><c:out value="${pihak.alamat3}"/></font></td></tr>
                                                </c:if>
                                                <c:if test="${pihak.alamat4 ne null}">
                                                    <tr><td><font size="-1"><c:out value="${pihak.alamat4}"/></font></td></tr>
                                                </c:if>
                                                <tr><td><font size="-1"><c:out value="${pihak.poskod}"/></font></td></tr>
                                                <tr><td><font size="-1"><c:out value="${pihak.negeri.nama}"/></font></td></tr>
                                                <c:set value="${i + 1}" var="i"/>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Ahli Lembaga Pengarah :</font></td>
                                    <td  id="tdDisplay">
                                        <table border="0" width="80%">
                                            <c:set value="1" var="count"/>
                                            <c:forEach items="${senaraiPihak}" var="pihak" >
                                                <c:forEach items="${pihak.senaraiPengarah}" var="pengarah" >
                                                    <tr><td><font size="-1">
                                                                <c:if test ="${pengarah.nama ne null}">
                                                                    <c:out value="${pengarah.nama}"/>
                                                                </c:if>
                                                                <c:if test ="${pengarah.nama eq null}">
                                                                    -
                                                                </c:if>
                                                            </font></td>
                                                    </tr>
                                                    <c:set value="${count + 1}" var="count"/>
                                                </c:forEach>
                                            </c:forEach>    
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td></tr>

                    <tr><td><b>7. TARIKH TERIMA PERMOHONAN</b></td></tr>
                    <tr><td>&nbsp;&nbsp;&nbsp;&nbsp; <s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhpermohonan}"/></td></tr>

                    <tr><td><b>8. BAYARAN/NO. RESIT </b></td></tr>
                    <tr><td><font style="text-transform:capitalize">&nbsp;&nbsp;&nbsp;&nbsp; RM${actionBean.kewTrans.amaun}/${actionBean.kewTrans.dokumenKewangan.idDokumenKewangan} &nbsp;</font></td></tr>                   

                    <tr><td><b>9. LATAR BELAKANG</b></td></tr>
                    <c:if test="${edit}">
                        <tr>
                            <td>
                                <table border="0" width="96%" id="tbllatarbkg">
                                    <tr>
                                        <td valign="top"><b>9.1</b></td>
                                        <td><s:textarea name="latarbelakang" rows="5" cols="150" class="normal_text"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>9.1 ${actionBean.latarbelakang}&nbsp;</td></tr>
                    </c:if>
                    <tr><td><b>9.2 Butir-butir Pembangunan Yang Dipohon Mesyuarat JKBB Pada <s:format formatPattern="dd MMM yyyy" value="${actionBean.tarikhpermohonan}"/> </b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;
                                <table class="tablecloth" border="1" width="70%" id="tbl">
                                    <tr>
                                        <th width="5%" align="center"><b>Bil</b></th>
                                        <th width="27%" align="center"><b>Pembangunan</b></th>
                                        <th width="18%" align="center"><b>Unit</b></th>
                                        <th width="18%" align="center"><b>Keluasan</b></th>
                                        <th width="27%" align="center"><b>Harga</b></th>
                                    </tr>                            
                                    <c:set var="recordCount" value="0"/>
                                    <c:set var="billNo" value="0"/>
                                    <c:set value="0" var="i"/>
                                    <c:forEach items="${actionBean.senaraiKertasButiran}" var="line">
                                        <tr>
                                            <td><c:out value="${i+1}"/></td>
                                            <td><s:text name="pembangunan${i}" id="pembangunan${i}" value="${line.pembangunan}" size="35" class="normal_text"/></td>
                                            <td><s:text name="unit${i}" id="unit${i}" value="${line.unit}" size="20" class="normal_text"/></td>
                                            <td><s:text name="luas${i}" id="luas${i}" value="${line.luas}" size="20" class="normal_text"/></td>
                                            <td><s:text name="hargaUnit${i}" id="hargaUnit${i}" value="${line.hargaSeunit}" size="35" class="normal_text"/></td>
                                            <td>
                                                <div align="center">
                                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                                         id='rem_${line_rowNum}' onclick="removeKertasButiran('${line.idKertasBtr}')"  onmouseover="this.style.cursor='pointer';">
                                                </div>
                                            </td>
                                            <s:hidden name="idKertasBtr${i}" id="idKertasBtr${i}" value="${line.idKertasBtr}" />
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <tr><!-- Hidden field for recordCount -->
                            <td><s:hidden name="count" id="count" value="${(fn:length(actionBean.senaraiKertasButiran))}" />
                                <s:hidden name="testCount" id="testCount" value="${(fn:length(actionBean.senaraiKertasButiran))}" /></td>
                        </tr>
                        <tr>
                            <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow('tbl','count')"/>
                                <%--<s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl','count')"/>--%></td>
                        </tr>                
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;
                                <table class="tablecloth" border="1" width="80%">
                                    <tr>
                                        <th width="5%" align="center"><b>Bil</b></th>
                                        <th width="24%" align="center"><b>Pembangunan</b></th>
                                        <th width="24%" align="center"><b>Unit</b></th>
                                        <th width="24%" align="center"><b>Keluasan</b></th>
                                        <th width="23%" align="center"><b>Harga</b></th>

                                        <c:set var="recordCount" value="0"/>
                                        <c:set value="1" var="i"/>
                                        <c:forEach items="${actionBean.senaraiKertasButiran}" var="line">
                                        <tr>
                                            <td width="5%"><center>${i} </center></td>
                                            <td width="24%"><center>${line.pembangunan} </center></td>
                                            <td width="24%"><center>${line.unit} </center></td>
                                            <td width="24%"><center>${line.luas} </center></td>
                                            <td width="24%"><center>${line.hargaSeunit} </center></td>
                                        </tr>
                                        <c:set var="i" value="${i+1}"/>
                                    </c:forEach>                              
                                </table>
                            </td>
                        </tr>                
                    </c:if>
                    <tr>
                        <td>i) Penjualan unit yang diperuntukkan kepada Orang Melayu hendaklah diberi potongan(diskaun)
                            berdasarkan harga dan peratusan seperti berikut :- </td>
                    </tr>
                    <tr>
                        <td>
                            <table class="tablecloth" border="1" width="50%">
                                <tr>
                                    <th width="5%" align="center"><b>Bil</b></th>
                                    <th width="45%" align="center"><b>Harga</b></th>
                                    <th width="45%" align="center"><b>Peratusan Potongan (diskaun)</b></th>
                                </tr>
                                <tr align="center">
                                    <td>a)</td>
                                    <td> RM 25,001 - RM 60,000 </td>
                                    <td> 5% </td>
                                </tr>
                                <tr>
                                    <td>b)</td>
                                    <td> RM 60,001 - RM 100,000 </td>
                                    <td> 7.5% </td>
                                </tr>
                                <tr>
                                    <td>c)</td>
                                    <td> RM 100,001 - RM 150,000 </td>
                                    <td> 10% </td>
                                </tr>
                                <tr>
                                    <td>d)</td>
                                    <td> RM 150,001 - RM 200,000 </td>
                                    <td> 12.5% </td>
                                </tr>
                                <tr>
                                    <td>a)</td>
                                    <td> RM 200,001 ke atas </td>
                                    <td> 15% </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr id="rayuan">                    
                        <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;
                                <table class="tablecloth" border="1" width="80%" id="tb2">
                                    <tr>
                                        <th width="5%" align="center"><b>Bil</b></th>
                                        <th width="32%" align="center"><b>Tarikh</b></th>
                                        <th width="32%" align="center"><b>Jenis Rayuan</b></th>
                                        <th width="32%" align="center"><b>Keputusan</b></th>
                                    </tr>
                                    <c:set var="recordCount" value="0"/>
                                    <c:set var="billNo" value="0"/>
                                    <c:set value="0" var="i"/>
                                    <c:forEach items="${actionBean.senaraiKertasKpsn}" var="line">
                                        <tr>
                                            <td width="5%"><center>${i+1}</center></td>
                                            <td align="left"><s:text name="tarikhRayuan${i}" id="tarikhRayuan${i}" formatPattern="dd/MM/yyyy" size="25" onmouseover="javascript:setDatepicker(${i});" onclick="javascript:setDatepicker(${i});" value="${line.trhRayuan}"/></td>
                                            <td align="left"><s:textarea name="jenisRayuan${i}" id="jenisRayuan${i}" rows="6" cols="40" class="normal_text">${line.jenisRayuan}</s:textarea></td>
                                            <td align="left"><s:textarea name="kpsn${i}" id="kpsn${i}" rows="6" cols="40" class="normal_text">${line.keputusan}</s:textarea></td>
                                                <td>
                                                    <div align="center">
                                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                                         id='rem_${line_rowNum}' onclick="removeKertasKeputusan('${line.idKertasKpsn}')"  onmouseover="this.style.cursor='pointer';">
                                                </div>
                                            </td>
                                            <s:hidden name="idKertasKpsn${i}" id="idKertasKpsn${i}" value="${line.idKertasKpsn}" />
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <tr><!-- Hidden field for recordCount -->
                            <td><s:hidden name="count2" id="count2" value="${(fn:length(actionBean.senaraiKertasKpsn))}" />
                                <s:hidden name="testCount2" id="testCount2" value="${(fn:length(actionBean.senaraiKertasKpsn))}" /></td>
                        </tr>
                        <tr>
                            <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow2('tb2','count2')"/>
                                <%--<s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow2('tb2','count2')"/>--%></td>
                        </tr>

                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;
                                <table class="tablecloth" border="1" width="80%">
                                    <tr>
                                        <th width="5%" align="center"><b>Bil</b></th>
                                        <th width="15%" align="center"><b>Tarikh</b></th>
                                        <th width="32%" align="center"><b>Jenis Rayuan</b></th>
                                        <th width="32%" align="center"><b>Keputusan</b></th>
                                    </tr>
                                    <c:set var="recordCount" value="0"/>
                                    <c:set var="billNo" value="0"/>
                                    <c:set value="0" var="i"/>
                                    <c:forEach items="${actionBean.senaraiKertasKpsn}" var="line">
                                        <tr>
                                            <td width="5%"><center>${i+1}</center></td>
                                            <td width="15%"><center><fmt:formatDate value="${line.trhRayuan}" pattern="dd/MM/yyyy"/></center></td>
                                            <td width="24%"><center>${line.jenisRayuan}</center></td>
                                            <td width="24%"><center>${line.keputusan}</center></td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>                              
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    </tr>

                    <tr><td><b>10. ALASAN RAYUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="alasanrayuan" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>10.1 ${actionBean.alasanrayuan}</td></tr>
                    </c:if>

                    <tr><td><b>11. PERAKUAN PENGARAH TANAH DAN GALIAN MELAKA</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="perakuan" rows="5" cols="150" id="perakuan" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>11.1 ${actionBean.perakuan}</td></tr>
                    </c:if>

                    <tr><td><b>12. KEPUTUSAN YANG DIPOHON</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="keputusan" rows="5" cols="150" id="keputusan" class="normal_text" /></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>12.1 ${actionBean.keputusan}</td></tr>
                    </c:if>

                </table>

                <p align="center">                  
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>                   
                    <%-- <c:if test="${actionBean.stageId eq 'rekodrayuanterikinitangguh'}">
                         <s:button name="kertasBaru" id="kertasBaru" value="Kertas Baru" class="longbtn" onclick="validation(this.name, this.form);"/>
                     </c:if>
                    --%>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>



<%--
                       <c:if test="${edit}">
                       <tr><td>
                              <c:set var="i" value="0"/>
                              <c:forEach items="${actionBean.senaraiPemohon}" var="pemohon" >
                               <table border="0" width="60%">
                                   <tr>
                                       
                                       <td  id="tdLabel"><font color="black">5.${i+1} a) Pemohon :</font></td>
                                       <td  id="tdDisplay">
                                           <table border="0" width="80%">
                                                   <tr>
                                                       <td>
                                                           <s:text name="senaraiPemohon[${i}].pihak.nama" id="pihakName" size="30" class="normal_text" />
                                                       </td>
                                                   </tr>
                                           </table>
                                       </td>
                                   </tr>
                               <tr>
                                   
                                   <td  id="tdLabel" valign="top"><font color="black">b) Alamat :</font></td>
                                   <td  id="tdDisplay">
                                       <table border="0" width="80%">
                                               <tr><td><s:text name="senaraiPemohon[${i}].pihak.suratAlamat1" id="suratAlamat1" size="30" class="normal_text"/></td></tr>
                                               <tr><td><s:text name="senaraiPemohon[${i}].pihak.suratAlamat2" id="suratAlamat2" size="30" class="normal_text"/></td></tr>
                                               <tr><td><s:text name="senaraiPemohon[${i}].pihak.suratAlamat3" id="suratAlamat3" size="30" class="normal_text"/></td></tr>
                                               <tr><td><s:text name="senaraiPemohon[${i}].pihak.suratAlamat4" id="suratAlamat4" size="30" class="normal_text"/></td></tr>
                                               <tr><td><s:text name="senaraiPemohon[${i}].pihak.suratPoskod" id="suratPoskod" size="30" class="normal_text"/></td></tr>
                                               <tr><td><s:select name="suratNegeri${i}" id="suratAlamat4" value="${pemohon.pihak.suratNegeri.kod}">
                                                           <s:option value="">Sila Pilih</s:option>
                                                           <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                                       </s:select>
                                                   </td>
                                               </tr>
                                       </table>
                                   </td>
                               </tr>
                               <tr>
                                  
                                   <td id="tdLabel" valign="top"><font color="black">Lembaga Pengarah :</font></td>
                                   <td  id="tdDisplay">
                                       <table border="0" width="80%">
                                           <c:set value="0" var="j"/>
                                           <c:set scope="request" value="${actionBean.listPengarah}" var="senaraiPengarah"/>
                                           <c:forEach items="${pemohon.pihak.senaraiPengarah}" var="pengarah" >
                                               <tr>
                                                   <td>${j+1}. &nbsp; </td>
                                                   <td valign="top">  <s:text name="senaraiPemohon[${i}].pihak.senaraiPengarah[${j}].nama" id="pengarahNama" size="30" class="normal_text"/>
                                                       
                                                   </td>
                                               </tr>
                                           <c:set value="${j + 1}" var="j"/>
                                           </c:forEach>
                                       </table>
                                   </td>
                               </tr>
                           </table>
                              <c:set var="i" value="${i + 1}"/>
                           </c:forEach>
                           </td></tr>
                        </c:if>
                       
<%--
<c:if test="${!edit}">
<tr><td>
       <c:set var="i" value="0"/>
       <c:forEach items="${actionBean.senaraiPemohon}" var="pemohon" >
        <table border="0" width="60%">
            <tr>
               
               <td  id="tdLabel" valign="top"><font color="black">5.${i+1} a) Pemohon :</font></td>
                <td  id="tdDisplay">
                    <table border="0" width="80%">                                        
                            <tr>                                                
                                <td><font size="-1">
                                        <c:out value="${pemohon.pihak.nama}"/>
                                    </font>                                                   
                                </td>
                            </tr>
                    </table>
                </td>
            </tr>
        <tr>
            
            <td  id="tdLabel" valign="top"><font color="black">b) Alamat :</font></td>
            <td  id="tdDisplay">
                <table border="0" width="80%">                                    
                    <tr><td><font size="-1">
                                <c:out value="${pemohon.pihak.suratAlamat1}"/>&nbsp;
                            </font>                                            
                            </td>
                        </tr>
                        <c:if test="${pemohon.pihak.suratAlamat2 ne null}">
                            <tr><td><font size="-1"><c:out value="${pemohon.pihak.suratAlamat2}"/>&nbsp;</font></td></tr>
                        </c:if>
                        <c:if test="${pemohon.pihak.suratAlamat3 ne null}">
                            <tr><td><font size="-1"><c:out value="${pemohon.pihak.suratAlamat3}"/>&nbsp;</font></td></tr>
                        </c:if>
                        <c:if test="${pemohon.pihak.suratAlamat4 ne null}">
                            <tr><td><font size="-1"><c:out value="${pemohon.pihak.suratAlamat4}"/>&nbsp;</font></td></tr>
                        </c:if>
                        <tr><td><font size="-1"><c:out value="${pemohon.pihak.suratPoskod}"/>&nbsp;</font></td></tr>
                        <tr><td><font size="-1"><c:out value="${pemohon.pihak.suratNegeri.nama}"/>&nbsp;</font></td></tr>
                   
                    
                </table>
            </td>
        </tr>
        <tr>
           
            <td id="tdLabel" valign="top"><font color="black">c) Lembaga Pengarah :</font></td>
            <td  id="tdDisplay">
                <table border="0" width="80%">
                    <c:set value="1" var="count"/>
                    <c:forEach items="${pemohon.pihak.senaraiPengarah}" var="pengarah" >
                        <tr><td><font size="-1">
                                    <c:out value="${count}"/>) &nbsp;
                                    <c:out value="${pengarah.nama}"/>
                                </font></td>
                        </tr>
                    <c:set value="${count + 1}" var="count"/>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
       <c:set var="i" value="${i + 1}"/>
    </c:forEach>
    </td></tr>
 </c:if>
--%>