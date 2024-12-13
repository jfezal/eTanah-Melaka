<%--
    Document   : diari_siasatan_IO
    Created on : July 31, 2012, 3:16:43 PM
    Author     : sitifariza.hanim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>

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
    p{
        color:black;
        font-weight:normal;
        font-size:13px;
        padding-top:2px;
        padding-bottom:2px;
        line-height: 15pt ;
        text-align: justify;
    }
</style>

<script language="javascript" type="text/javascript">

    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function validateForm(){

        var bil =  document.getElementById("recordCount").value;
        var tajuk = document.getElementById('tajuk');

        if(tajuk.value == "" ){
            alert("Sila isikan Tajuk Kertas Butiran penyiasatan terlebih dahulu");
            $('#tajuk').focus();
            return false;
        }

        if(document.getElementById("recordCount").value == "" ){
            alert("Sila isikan  Butiran penyiasatan terlebih dahulu");
            return false;
        }

        for (var i = 0; i < bil; i++){

            var kandungan = document.getElementById('kandungan'+i);
            var tarikh = document.getElementById('tarikh'+i);
            var jam = document.getElementById('jam'+i);
            var minit = document.getElementById('minit'+i);
            var ampm = document.getElementById('ampm'+i);

            if(kandungan.value == "" ){
                alert("Sila isikan Butiran penyiasatan terlebih dahulu");
                $('#kandungan'+i).focus();
                return false;
            }
            if(tarikh.value == "" ){
                alert("Sila isikan Tarikh terlebih dahulu");
                $('#tarikh'+i).focus();
                return false;
            }
            if(jam.value == "" ){
                alert("Sila isikan masa laporan terlebih dahulu");
                $('#jam'+i).focus();
                return false;
            }

            if (jam.value >= 13){
                alert("Sila masukkan bilangan 1 sampai 12 sahaja.");
                $('#jam'+i).focus();
                return false;
            }

            if(minit.value == "" ){
                alert("Sila isikan masa laporan terlebih dahulu");
                $('#minit'+i).focus();
                return false;
            }

            if (minit.value >= 59){
                alert("Sila masukkan bilangan 0 sampai 59 sahaja.");
                $('#minit'+i).focus();
                return false;
            }

            if(ampm.value == "" ){
                alert("Sila pilih pagi atau petang pada bahagian masa laporan");
                $('#ampm'+i).focus();
                return false;
            }

        }
        return true;
    }

    function addRow (tableid){

        var table = document.getElementById (tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        document.getElementById("recordCount").value =rowcount;

        var id="toDate"+(rowcount-1);

        var cell1 = row.insertCell(0);
        var e1 = document.createElement("INPUT");
        e1.setAttribute("type","checkbox");
        e1.setAttribute("name","pilih" +(rowcount-1));
        cell1.appendChild(e1);

        var cell2 = row.insertCell(1);
        var bil = document.createTextNode(rowcount);
        cell2.appendChild(bil);


        var cell3 = row.insertCell(2);
        var e2 = document.createElement("textarea");
        e2.t = "kandungan"+(rowcount-1);
        e2.rows = 3;
        e2.cols = 64;
        e2.name ="kandungan"+(rowcount-1);
        e2.id ="kandungan"+(rowcount-1);
    <%--e2.onchange = function(){convertText(this,"kandungan" +(rowcount-1));};--%>
            cell3.appendChild(e2);

            var cell4 = row.insertCell(3);
            var e3 = document.createElement("INPUT");
            e3.setAttribute("type","text");
            e3.setAttribute("name","tarikh"+(rowcount-1));
            e3.setAttribute("id","tarikh"+(rowcount-1));
            e3.setAttribute("formatPattern", "dd/MM/yyyy");
            e3.setAttribute("formatType", "datepicker");
            e3.setAttribute("size", '12');
            e3.onmouseover = function(){setDatepicker(id,rowcount);};
            e3.onfocus = function(){setDatepicker(id,rowcount);};
            cell4.appendChild(e3);

            var cell5 = row.insertCell(4);
            var e7 =document.createTextNode(' ');
            var e8 =document.createTextNode(' ');
            var e4 = document.createElement("select");
            e4.setAttribute("name","jam"+(rowcount-1));
            e4.setAttribute("id","jam" +(rowcount-1));

                var option1 = document.createElement("option");
                option1.text = "Jam";
                option1.value ="";
                e4.options.add(option1);



    <c:forEach var="hour" begin="1" end="12">
            var option2 = document.createElement("option");
        <c:choose>
            <c:when test="${hour > 9}">
                    var textVal2=document.createTextNode("${hour}");
                    option2.appendChild(textVal2);
                    option2.setAttribute("value","${hour}");
            </c:when>
            <c:otherwise>
                    var textVal2=document.createTextNode("0${hour}");
                    option2.appendChild(textVal2);
                    option2.setAttribute("value","0${hour}");
            </c:otherwise>
        </c:choose>
                e4.appendChild(option2);
    </c:forEach>


            var e5 = document.createElement("select");
            e5.setAttribute("name","minit"+(rowcount-1));
            e5.setAttribute("id","minit" +(rowcount-1));

                var option3 = document.createElement("option");
                option3.text = "Minit";
                option3.value ="";
                e5.options.add(option3);


    <c:forEach var="minute" begin="0" end="59">
            var option4 = document.createElement("option");
        <c:choose>
            <c:when test="${minute > 9}">
                    var textVal2=document.createTextNode("${minute}");
                    option4.appendChild(textVal2);
                    option4.setAttribute("value","${minute}");
            </c:when>
            <c:otherwise>
                    var textVal2=document.createTextNode("0${minute}");
                    option4.appendChild(textVal2);
                    option4.setAttribute("value","0${minute}");
            </c:otherwise>
        </c:choose>
                e5.appendChild(option4);
    </c:forEach>



            var e6 = document.createElement("select");
                 e6.setAttribute("name","ampm"+(rowcount-1));
                 e6.setAttribute("id","ampm" +(rowcount-1));
                 e6.setAttribute("style","width:75px;");
               
            //for "sila pilih"
            var option5 = document.createElement("option");
                option5.text = " Pilih";
                option5.value ="";
                e6.options.add(option5);

            var option6 = document.createElement("option");
                option6.text = "Pagi";
                option6.value ="am";
                e6.options.add(option6);

            var option7 = document.createElement("option");
                option7.text = "Petang";
                option7.value ="pm";
                e6.options.add(option7);

            cell5.appendChild(e4);
            cell5.appendChild(e7);
            cell5.appendChild(e5);
            cell5.appendChild(e8);
            cell5.appendChild(e6);

    <%--     var cell6 = row.insertCell(4);
         var e5 = document.createElement("IMG");
         e5.setAttribute("alt","Klik Untuk Hapus");
         e5.setAttribute("border","0");
         e5.setAttribute("src","${pageContext.request.contextPath}/images/not_ok.gif");
         e5.setAttribute("class","rem");
         e5.setAttribute("id","rem_${line_rowNum}");
         e5.onclick=function(){deleteRow(this,'tbl');};
         cell6.style.textAlign = "center";
         cell6.appendChild(e5);--%>
             }

             function setDatepicker(id,rowcount){
                 $("#tarikh"+(rowcount-1)).datepicker({dateFormat: 'dd/mm/yy'});
             }

             function deleteRow(r,tableid){
                 alert(tableid);
                 if(confirm('Adakah anda pasti untuk hapus?')) {
                     var i=r.parentNode.parentNode.rowIndex;
                     document.getElementById('tbl').deleteRow(i);
                 }
                 regenerateBill(tableid);

             }

             function deleteRow1(tableid) {
                 var idKandungan;
                 try {
                     var table = document.getElementById(tableid);
                     var rowCount = table.rows.length;
                     var j=0;
                     for(var i=0; i<rowCount; i++) {
                         var row = table.rows[i];
                         var chkbox = row.cells[0].childNodes[0];
                         if(null != chkbox && true == chkbox.checked) {
                             idKandungan = $("#idKertas"+(i+j-1)).val();
                             if(document.getElementById("idKertas"+(i+j-1)) != null){
                                 var url = '${pageContext.request.contextPath}/penguatkuasaan/diari_siasatan_IO?deleteDiari&idKandungan='
                                     +idKandungan;

                                 $.get(url,
                                 function(data){
                                     $('#page_div').html(data);
                                 },'html');
                             }

                             table.deleteRow(i);
                             rowCount--;
                             i--;
                             j++;

                         }
                     }
                 }catch(e) {
                     alert(e);
                 }

                 regenerateBill(tableid);
             }

             function regenerateBill(tableid){
                 try{
                     var table = document.getElementById(tableid);
                     var rowCount = table.rows.length;
                     document.getElementById("recordCount").value =rowCount-1;

                     if(rowCount > 1){
                         for(var i=1;i<rowCount;i++){
                             table.rows[i].cells[1].childNodes[0].data= i;
                         }
                     }

                 }catch(e){
                     alert("Error in regenerateBill : "+e);
                 }
             }


             function removeDiari(idKandungan){

                 if(confirm('Adakah anda pasti untuk hapus?')) {
                     var url = '${pageContext.request.contextPath}/penguatkuasaan/diari_siasatan_IO?deleteDiari&idKandungan='+idKandungan;
                     $.get(url,
                     function(data){
                         $('#page_div').html(data);
                     },'html');
                 }
             }


             function convertText(r,t){
                 var i = r.value;
                 document.getElementById(t).value=i.toUpperCase();
             }

    <%--   function semakIdHakmilik(){
           var idHakmilik= $("#idHakmilik").val();
           alert("hai : " +idHakmilik);
           if(idHakmilik != ""){
               alert("tak null");
               $.get('${pageContext.request.contextPath}/penguatkuasaan/diari_siasatan_IO?semakIdHakmilik&idHakmilik='+idHakmilik,
               function(data){
                   alert(data);
                   if(data == "exist"){
                       alert("Id Hakmilik wujud");
                   }else{
                       alert("Id Hakmilik tidak wujud. Sila masukkan Id Aduan yang betul.");
                   }
               }, 'html');

        }

    }--%>

        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

        function validateFormUlasan(){

            if($('#ulasan').val() == ''){
                alert("Sila masukkan ulasan.");
                $('#ulasan').focus();
                return false;
            }
            return true;
        }

        function popup(idKertas){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/diari_siasatan_IO?showForm4&idKertas='+idKertas;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");


        }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.DiariSiasatanIOActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.idPermohonan" />
   <%-- <s:text name="permohonan.idPermohonan" />--%>
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Diari Siasatan Pegawai Penyiasat</legend>
                <p>
                    <label><em>*</em>Tajuk :</label>
                    <s:textarea name="tajuk" id="tajuk" cols="70" rows="10" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);" class="normal_text"> </s:textarea>
                </p>
                <%--  <p>
                      <label>Rujuk Fail :</label>
                      <s:text name="kertas.hakmilik.idHakmilik"id="idHakmilik" onchange="semakIdHakmilik()" maxlength="25"></s:text>
                      ${actionBean.kertas.hakmilik.idHakmilik}
                  </p>--%>

                <s:hidden name="recordCount" id="recordCount"/>
                <div class="content" align="center">
                    <table  width="75%" id="tbl" class="tablecloth">
                        <tr>
                            <th  width="1%" align="center"><b>Pilih</b></th>
                            <th  width="1%" align="center"><b>Bil</b></th>
                            <th  width="3%" align="center"><b><em>*</em>Butiran Penyiasatan</b></th>
                            <th  width="1%" align="center"><b><em>*</em>Tarikh</b></th>
                            <th  width="30%" align="center"><b><em>*</em>Masa</b></th>
                            <%--<th  width="5%" align="center"><b>Hapus</b></th>--%>
                        </tr>
                        <c:set value="0" var="i"/>
                        <c:forEach items="${actionBean.senaraiKertas}" var="line">
                            <tr style="font-weight:bold">

                                <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                <td><c:out value="${i+1}"/></td>
                                <td><font>
                                        <s:textarea name="kandungan${i}" id="kandungan${i}" value="${line.kandungan}" cols="56" rows="3" class="normal_text"/>
                                    </font>
                                </td>
                                <td>
                                    <%--<s:text name="tarikh${i}" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikh${i}" value="${line.tarikhButiran}" onmouseover="setDatepicker(this.name,${i})"/>&nbsp;--%>
                                    <s:text name="tarikh${i}" id="tarikh${i}" value="${line.tarikhButiran}" size="12" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;
                                </td>

                                <%--to set value for AM/PM--%>
                                <c:set var="waktu" value="${fn:substring(line.tarikhButiran,11,13)}"/>
                                <c:if test="${waktu > 11}"><c:set var="time" value="PM"/></c:if>
                                <c:if test="${waktu <= 11}"><c:set var="time" value="AM"/></c:if>
                                <%--to set value for hour--%>
                                <fmt:formatDate var="hourVal" value="${line.tarikhButiran}" pattern="hh" />
                                <%--to set value for minute--%>
                                <fmt:formatDate var="minuteVal" value="${line.tarikhButiran}" pattern="mm" />
                                <td>
                                    <s:select name="jam${i}" id="jam${i}" value="${hourVal}">
                                        <s:option value=""> Jam </s:option>
                                        <c:forEach var="hour" begin="1" end="12">
                                            <c:choose>
                                                <c:when test="${hour > 9}"><s:option value="${hour}">${hour}</s:option></c:when>
                                                <c:otherwise><s:option value="0${hour}">0${hour}</s:option></c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </s:select>
                                    <s:select name="minit${i}" id="minit${i}" value="${minuteVal}">
                                        <s:option value=""> Minit </s:option>
                                        <c:forEach var="minute" begin="00" end="59" >
                                            <c:choose>
                                                <c:when test="${minute > 9}"><s:option value="${minute}">${minute}</s:option></c:when>
                                                <c:otherwise><s:option value="0${minute}">0${minute}</s:option></c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </s:select>
                                    <s:select name="ampm${i}" id="ampm${i}" value="${time}" style="width:75px">
                                        <s:option value="">Pilih</s:option>
                                        <s:option value="AM">PAGI</s:option>
                                        <s:option value="PM">PETANG</s:option>
                                    </s:select>
                                </td>
                                <s:hidden name="idKertas${i}" id="idKertas${i}" value="${line.idKandungan}" />
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>

                    </table>
                    <br/>

                    <table width="80%">
                        <tr><td align="center">
                                <p align="center">** Sila tanda di petak Pilih untuk hapus.</p>
                                <br/>
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                                <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow1('tbl')"/>
                    </table>

                </div>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Diari Siasatan Pegawai Penyiasat</legend>

                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiKertasIO}" id="line" class="tablecloth" pagesize="10">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="ID Diari"><ul><a class="popup" onclick="popup(${line.idKertas})">${line.idKertas}</a></ul></display:column>
                        <display:column property="kodDokumen.nama" title="Dokumen" />
                        <display:column property="tajuk" title="Tajuk" />
                    </display:table>
                </div>
           </fieldset>
        </div>
    </c:if>
    <c:if test="${view2}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Tajuk</legend>
                <p>
                    ${actionBean.tajuk}&nbsp;
                </p>

                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiKertas}" id="line" class="tablecloth" pagesize="10">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column property="kandungan" title="Butiran Penyiasatan" />
                        <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhButiran}"/></display:column>
                        <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhButiran}"/></display:column>
                    </display:table><br/>
                    <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </div>
                
            </fieldset>
        </div>
       <%-- <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' && actionBean.kodNegeri eq '04'}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <p>
                        <label>Ulasan Penolong Pegawai Tanah Kanan : </label>
                        <c:if test="${actionBean.ulasan ne null}"> ${actionBean.ulasan}&nbsp; </c:if>
                        <c:if test="${actionBean.ulasan eq null}"> Tiada Data </c:if>

                    </p>

                </fieldset>
            </div>
        </c:if>--%>
    </c:if>

<%--    <c:if test="${ulasanPPTK}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Tajuk</legend>
                <p>
                    ${actionBean.tajuk}&nbsp;
                </p>

                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiKertas}" id="line" class="tablecloth" pagesize="10">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column property="kandungan" title="Butiran Penyiasatan" />
                        <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhButiran}"/></display:column>
                        <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhButiran}"/></display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <p>
                    <label><em>*</em>Ulasan Penolong Pegawai Tanah Kanan : </label>
                    <s:textarea name="ulasan" id ="ulasan"  cols="70" rows="10" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" />&nbsp;

                </p>
                <div id ="buttonPPTK" align="center">
                    <s:button name="simpanUlasan" id="save"  value="Simpan" class="btn"   onclick="if(validateFormUlasan())doSubmit(this.form, this.name, 'page_div');" />
                </div>
            </fieldset>
        </div>
    </c:if>--%>
</s:form>

