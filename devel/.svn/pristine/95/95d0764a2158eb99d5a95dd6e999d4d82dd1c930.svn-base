<%--
    Document   : popup_rekod_perbicaraan : for MELAKA sek 422,423,424,427,428,429
    Created on : Created on : Sep 22, 2013, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageRekodPerbicaraan();
            //alert("Maklumat telah berjaya disimpan.");
            self.close();
        },'html');

    }

    function validateForm(){
        return true;
    }

    function test(f) {
        $(f).clearForm();
    }

    function convertText(r,t){
        var i = r.value;
        document.getElementById(t).value=i.toUpperCase();
    }
    
    function addRow (tableid){

        var table = document.getElementById (tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        document.getElementById("recordCountPerbicaraan").value =rowcount;

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
        e2.t = "catatan"+(rowcount-1);
        e2.rows = 3;
        e2.cols = 40;
        e2.name ="catatanPerbicaraan"+(rowcount-1);
        e2.id ="catatanPerbicaraan"+(rowcount-1);
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

        }
        
        function setDatepicker(id,rowcount){
            $("#tarikh"+(rowcount-1)).datepicker({dateFormat: 'dd/mm/yy'});
        }
        
        function deleteRow(tableid) {
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var idPeranan;
                try {
                    var table = document.getElementById(tableid);
                    var rowCount = table.rows.length;
                    var j=0;
                    for(var i=0; i<rowCount; i++) {
                        var row = table.rows[i];
                        var chkbox = row.cells[0].childNodes[0];
                        if(null != chkbox && true == chkbox.checked) {
                            idPeranan = $("#idKehadiran"+(i+j-1)).val();

                            if(document.getElementById("idKehadiran"+(i+j-1)) != null){

                                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_mahkamah?deleteRecordKehadiran&id='
                                    +idPeranan;

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
            
        }

        function regenerateBill(tableid){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                document.getElementById("recordCount").value =rowCount-1;

                if(rowCount > 1){
                    for(var i=1;i<rowCount;i++){
                        table.rows[i].cells[1].childNodes[0].data= i;
                        
                        table.rows[i].cells[2].childNodes[0].name= 'nama'+(i-1);
                        table.rows[i].cells[2].childNodes[0].id= 'nama'+(i-1);
                        
                        table.rows[i].cells[3].childNodes[0].name= 'jawatan'+(i-1);
                        table.rows[i].cells[3].childNodes[0].id= 'jawatan'+(i-1);
                    }
                }

            }catch(e){
                alert("Error in regenerateBill : "+e);
            }
        }

 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatMahkamahActionBean">
    <s:messages />

    <%--for sek 422,423,424,427,428 & 429 melaka : kemaskini maklumat keputusan mahkamah dan tarikh keputusan--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Mahkamah
            </legend>
            <div class="content">
                <p>
                    <label>Kategori Mahkamah :</label>
                    ${actionBean.permohonanRujukanLuar.agensi.nama}
                </p>
                <p>
                    <label>Tempat Mahkamah :</label>
                    ${actionBean.namaSidang}&nbsp;
                </p>
                <p>
                    <label>No Rujukan :</label>
                    ${actionBean.noRujukan}&nbsp;
                </p>
                <p>
                    <label>Tarikh Sebutan :</label>
                    ${actionBean.tarikhSidang}&nbsp;
                </p>
                <p>
                    <label>Status :</label>
                    ${actionBean.permohonanRujukanLuar.keputusanPendakwaan.nama}&nbsp;
                </p>
                <p>
                    <label>Minit :</label>
                    ${actionBean.catatan}&nbsp;
                </p>
            </div>
            <br/><br/>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pihak Mahkamah
            </legend>
            <br/>

            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Pihak Mahkamah
                    </legend>
                    <div align="center" >
                        <display:table name="${actionBean.senaraipermohonanRujukanLuarPeranan}" id="line" class="tablecloth" >
                            <display:column title="Bil">
                                ${line_rowNum}
                            </display:column>
                            <display:column property="nama" title="Nama" />
                            <display:column title="Peranan" property="kodPerananRujukanLuar.nama"/>
                        </display:table>
                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <br/>
            </div>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Perbicaraan
            </legend>
            <br/>

            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Perbicaraan
                    </legend>
                    <div class="instr-fieldset">
                        Sila klik tambah untuk menambah senarai tarikh perbicaraan
                    </div>

                    <div align="center" >

                        <p>
                            <s:hidden name="recordCountPerbicaraan" id="recordCountPerbicaraan"/>
                        </p>


                        <table  width="90%" id="tbl" class="tablecloth" align="center">
                            <tr>
                                <th  width="1%" align="center"><b>Pilih</b></th>
                                <th  width="1%" align="center"><b>Bil</b></th>
                                <th  width="1%" align="center"><b>Catatan</b></th>
                                <th  width="5%" align="center"><b>Tarikh</b></th>
                                <th  width="10%" align="center"><b>Masa</b></th>
                            </tr>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.senaraiPerbicaraan}" var="line">
                                <tr style="font-weight:bold">
                                    <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                    <td><c:out value="${i+1}"/></td>
                                    <td> <s:textarea name="catatanPerbicaraan${i}" id="catatanPerbicaraan${i}" value="${line.catatan}" cols="40" rows="3" class="normal_text"/>
                                    <td>
                                        <s:text name="tarikh${i}" id="tarikh${i}" value="${line.tarikhKehadiran}" size="12" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;
                                    </td>
                                    <%--to set value for AM/PM--%>
                                    <c:set var="waktu" value="${fn:substring(line.tarikhKehadiran,11,13)}"/>
                                    <c:if test="${waktu > 11}"><c:set var="time" value="PM"/></c:if>
                                    <c:if test="${waktu <= 11}"><c:set var="time" value="AM"/></c:if>
                                    <%--to set value for hour--%>
                                    <fmt:formatDate var="hourVal" value="${line.tarikhKehadiran}" pattern="hh" />
                                    <%--to set value for minute--%>
                                    <fmt:formatDate var="minuteVal" value="${line.tarikhKehadiran}" pattern="mm" />
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
                                    <s:hidden name="idKehadiran${i}" id="idKehadiran${i}" value="${line.idKehadiran}" />
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>

                        </table>
                        <br/>
                        <p align="center"><em>**</em> Sila tanda di petak Pilih untuk hapus.</p>
                        <table width="80%">
                            <tr><td align="right">
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                    <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow('tbl')"/>
                        </table>
                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <br/>
                <p align="right">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idRujukan"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" value="Simpan" name="simpanRekodPerbicaraan"  onclick="if(validateForm())save(this.name,this.form);" />
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </div>
        </fieldset>
    </div>

</s:form>

