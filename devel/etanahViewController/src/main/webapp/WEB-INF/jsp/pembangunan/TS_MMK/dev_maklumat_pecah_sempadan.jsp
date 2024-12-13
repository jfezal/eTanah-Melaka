
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />--%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready( function() {
        $('#hakmilik').hide();
        $('#thakmilik').hide();
        $('#kerajaan').hide();

        var rowCount = document.getElementById("count").value;
        for(var i=0;i<rowCount;i++){
            if(document.getElementById("kodKategoriTanah"+i).value == 3){
                document.getElementById("bilanganTingkat"+i).disabled = true;
            }else{
                document.getElementById("bilanganTingkat"+i).disabled = false;
            }
        }

    });
</script>
<script type="text/javascript">
    function validateNumber2(index) {
        //if it is character, then remove it..
        var content = document.getElementById("noPlot"+index).value;
        if (isNaN(content)) {
            document.getElementById("noPlot"+index).value = RemoveNonNumeric(content);
            return;
        }
    }
    
    function validateNumber3(index) {
        //if it is character, then remove it..
        var content = document.getElementById("bilanganPlot"+index).value;
        if (isNaN(content)) {
            document.getElementById("bilanganPlot"+index).value = RemoveNonNumeric(content);
            return;
        }
    }
    
    function validateNumber4(index) {
        //if it is character, then remove it..
        var content = document.getElementById("luas"+index).value;
        if (isNaN(content)) {
            document.getElementById("luas"+index).value = RemoveNonNumeric(content);
            return;
        }
    }
    
    function dopopup(i,kod){
        var total = $('#jumlahPlot').val();
        if (kod == 'KHK'){
            var url = "showEditHakmilikPopup";
        } else if(kod == 'TKHK'){           
            var url = "showEditRizabPopup";
        } else {            
            var url = "showEditKerajaanPopup";
        }
    <%--var d = $('.x'+i).val();--%>
                var d=i;
                window.open("${pageContext.request.contextPath}/pembangunan/maklumat_pecahSempadan?"+url+"&idPlot="+d+"&plot="+total, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=950,height=600");
            }

            function refreshPageMaklumatPecahSempadan(){
                var url = '${pageContext.request.contextPath}/pembangunan/maklumat_pecahSempadan?refreshpage';
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }

            function checkPlot(){
                var jumlah = $('#jumlahPlot').val();
                var hakmilik = $('#hakmilik').val();
                var thakmilik = $('#thakmilik').val();
                var kerajaan = $('#kerajaan').val();
                var jumcurrent = parseInt(Number(hakmilik)) + parseInt(Number(thakmilik)) + parseInt(Number(kerajaan));
    <%--alert(jumcurrent);--%>
               if(jumcurrent == jumlah){
                   alert("Tiada lagi plot yang tinggal.");
                   $('#btnH').disable();
                   $('#btnR').disable();
                   $('#btnK').disable();
                   return true;
               }
               return false;
           }

           function addNewHakmilik(){
    <%--if(!checkPlot()){--%>
               var total = $('#jumlahPlot').val();
               window.open("${pageContext.request.contextPath}/pembangunan/maklumat_pecahSempadan?showHakmilikPopup"+"&plot="+total, "eTanah",
               "status=0,toolbar=0,location=0,menubar=0,width=950,height=600");
    <%--}--%>
          }

          function addNewRizab(){
    <%--if(!checkPlot()){--%>
              var total = $('#jumlahPlot').val();
              window.open("${pageContext.request.contextPath}/pembangunan/maklumat_pecahSempadan?showRizabPopup"+"&plot="+total, "eTanah",
              "status=0,toolbar=0,location=0,menubar=0,width=950,height=600");
    <%--}--%>
           }

           function addNewKerajaan(){
    <%--if(!checkPlot()){--%>
            var total = $('#jumlahPlot').val();
            window.open("${pageContext.request.contextPath}/pembangunan/maklumat_pecahSempadan?showKerajaanPopup"+"&plot="+total, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=950,height=600");
    <%--}--%>
          }

          function remove(val){
              if(confirm('Adakah anda pasti?')) {                 
                  $.blockUI({
                      message: $('#displayBox'),
                      css: {
                          top:  ($(window).height() - 50) /2 + 'px',
                          left: ($(window).width() - 50) /2 + 'px',
                          width: '50px'
                      }
                  }); 
                
                  var url = '${pageContext.request.contextPath}/pembangunan/maklumat_pecahSempadan?delete&idPlot='+val;
                  $.get(url,
                  function(data){
                      $('#page_div').html(data);
                      $.unblockUI();  
                  },'html');
              }
          }

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

    <%--Added code for Hakmilik--%>
        function validateSave(event, f){
    <%--alert("save");--%>                
                if( validateHakmilik()&& validateLuas()){
            
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top:  ($(window).height() - 50) /2 + 'px',
                            left: ($(window).width() - 50) /2 + 'px',
                            width: '50px'
                        }
                    });          
            
                    var q = $(f).formSerialize();
                    var url = f.action + '?' + event;
    <%--alert(url);--%>
                        $.post(url,q,
                        function(data){                                
                            $('#page_div',this.document).html(data);   
                            $.unblockUI();
                        },'html');           
                        return true;
                    }
                }
    
 
                function validateHakmilik(){
                    var rowCount = document.getElementById("count").value;
                    for(var i=0;i<rowCount;i++){
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'|| actionBean.permohonan.kodUrusan.kod eq 'SBPS'}">
                        if(document.getElementById("kodKategoriTanah"+i).value == ""){
                            alert('Sila Pilih Jenis Kategori.');
                            $('#kodKategoriTanah'+i).focus();
                            return false;
                        }
    </c:if>
                       if(document.getElementById("kodKegunaanTanah"+i).value == "" || document.getElementById("kodKegunaanTanah"+i).value == 0){
                           alert('Sila Pilih Kegunaan Tanah.');
                           $('#kodKegunaanTanah'+i).focus();
                           return false;
                       }
                       if(document.getElementById("bilanganPlot"+i).value == ""){
                           alert('Sila masukkan Bilangan Unit.');
                           $('#bilanganPlot'+i).focus();
                           return false;
                       }
                       if(document.getElementById("luas"+i).value == ""){
                           alert('Sila masukkan Luas.');
                           $('#luas'+i).focus();
                           return false;
                       }
    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PYTN'}">
                        if(document.getElementById("idHakmilik"+i).value == ""){
                            alert('Sila Pilih ID Hakmilik.');
                            $('#idHakmilik'+i).focus();
                            return false;
                        }
    </c:if>
                    }
                    return true;
                }

                function validateLuas(){
                    var jumlahLuas = document.getElementById("luas").value;
                    var rowCount = document.getElementById("count").value;
                    var sum=0;
                    for(var i=0;i<rowCount;i++){           
                        sum = (sum + ((parseFloat(Number(document.getElementById("luas"+i).value)))*(parseFloat(Number(document.getElementById("bilanganPlot"+i).value)))));
                    }
                    var jumluasTHakmilik = $('#jumluasTHakmilik').val();
                    var jumluasKerajaan = $('#jumluasKerajaan').val();
                    sum = (sum + (parseFloat(Number(jumluasTHakmilik))) + (parseFloat(Number(jumluasKerajaan))));
    <%--alert("jumlahLuas:"+jumlahLuas);
    alert("sum:"+sum);   
    alert("parseFloat(Number(jumlahLuas):"+parseFloat(Number(jumlahLuas)));
    alert("parseFloat(Number(sum)):"+parseFloat(Number(sum)));--%>
                if(parseFloat(Number(jumlahLuas)) < parseFloat(Number(sum))){            
                    alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                    return false;
                }        
                return true;
            }


            function validateBilTingkat(i){
    <%--alert("validateTingkat:"+(document.getElementById("kodKategoriTanah"+i).value));--%>
                if(document.getElementById("kodKategoriTanah"+i).value == 4){
                    document.getElementById("bilanganTingkat"+i).value="";
    <%--alert('Bilangan Tingkat.');--%>
                            document.getElementById("bilanganTingkat"+i).disabled = true;
                            return false;
                        }else{
                            document.getElementById("bilanganTingkat"+i).disabled = false;
                            return false;
                        }
                    }

                    function addRow(tableid){
                        var table = document.getElementById(tableid);
                        var rowcount = table.rows.length;
                        var row = table.insertRow(rowcount);
                        var i = parseInt(rowcount)-1;

    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'|| actionBean.permohonan.kodUrusan.kod eq 'SBPS'}">
                var cell0 = row.insertCell(0);
                var sel0 = document.createElement('select');
                sel0.name = 'kodKategoriTanah'+ i;
                sel0.id = 'kodKategoriTanah'+ i;
                sel0.onchange = function (){javascript:validateBilTingkat(i);};
                sel0.options[0] = new Option('--Sila Pilih--','');
        <c:set value="1" var="j" />
            <c:forEach items="${list.senaraiKodKategoriTanah}" var="kodKatg">
                   sel0.options[${j}] = new Option('${kodKatg.nama}','${kodKatg.kod}');
            <c:set value="${j+1}" var="j" />
        </c:forEach>
                   cell0.appendChild(sel0);
    </c:if>
    <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'|| actionBean.permohonan.kodUrusan.kod eq 'SBPS')}">
                var cell0 = row.insertCell(0);
                cell0.innerHTML ="${actionBean.hakmilik.kategoriTanah.nama}";
           
                var e1 = document.createElement ("INPUT");
                e1.setAttribute("Type", "hidden");
                e1.setAttribute("size","10");
                e1.setAttribute("name","kodKategoriTanah"+i);
                e1.setAttribute("id","kodKategoriTanah"+i);
                e1.setAttribute("value","${actionBean.hakmilik.kategoriTanah.kod}");
                cell0.appendChild(e1);
    </c:if>

                var cell1 = row.insertCell(1);
                var sel1 = document.createElement('select');
                sel1.name = 'kodKegunaanTanah'+ i;
                sel1.id = 'kodKegunaanTanah'+ i;
                sel1.style.width = '430px';
                sel1.options[0] = new Option('--Sila Pilih--','');
    <c:set var="p" value="1"/>
    <%--<c:forEach items="${list.senaraiKegunaanTanah}" var="kodKatg">
        sel1.options[${j}] = new Option('${kodKatg.nama}','${kodKatg.kod}');
        <c:set var="j" value="${j+1}" />
    </c:forEach>--%>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'|| actionBean.permohonan.kodUrusan.kod eq 'SBPS'}">
        <c:forEach items="${list.senaraiKodKategoriTanah}" var="kodKatg" >                
                    var optgroup = document.createElement("optgroup");
                    optgroup.label = "${kodKatg.nama}";                
            <c:set var="p" value="${p+1}" />
            <c:forEach items="${list.senaraiKegunaanTanah}" var="kodGuna" >
                <c:if test="${kodKatg.nama eq kodGuna.kategoriTanah.nama}">
                            var opt = document.createElement("option");
                            opt.label = "${kodGuna.nama}";
                            opt.value = "${kodGuna.kod}";
                            if ( typeof(opt.innerText) != 'undefined' ) {
                                opt.innerText = '${kodGuna.nama}';
                            }else {
                                opt.text = '${kodGuna.nama}';
                            }
                            optgroup.appendChild(opt);
                    <%--optgroup.appendChild(new Option('${kodGuna.nama}','${kodGuna.kod}'));--%>
                    <c:set var="p" value="${p+1}"/>
                </c:if>
            </c:forEach>
                        sel1.appendChild(optgroup);
        </c:forEach>
                    cell1.appendChild(sel1);
    </c:if>
    <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'|| actionBean.permohonan.kodUrusan.kod eq 'SBPS')}">
        <c:set var="j" value="1"/>
        <c:forEach items="${list.senaraiKegunaanTanah}" var="kodGuna" >
                <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq kodGuna.kategoriTanah.nama}">
                            sel1.options[${j}] = new Option('${kodGuna.nama}','${kodGuna.kod}');
                <c:set var="j" value="${j+1}" />
                <%--optgroup.appendChild(new Option('${kodGuna.nama}','${kodGuna.kod}'));--%>
            </c:if>
        </c:forEach>                   
                        cell1.appendChild(sel1);
    </c:if>

    <%--var cell2 = row.insertCell(2);
    var e2 = document.createElement ("INPUT");
    e2.setAttribute("Type", "text");
    e2.setAttribute("size","10");
    e2.setAttribute("name","listHakmilik["+i+"].bilanganTingkat");
    e2.setAttribute("id","bilanganTingkat"+i);
    e2.onkeyup = function (){javascript:validateNumber(i);};
    cell2.appendChild(e2);--%>           
        
                   var cell2 = row.insertCell(2);
                   var e2 = document.createElement ("INPUT");
                   e2.setAttribute("Type", "text");
                   e2.setAttribute("size","10");
                   e2.setAttribute("name","listHakmilik["+i+"].noPlot");
                   e2.setAttribute("id","noPlot"+i);
                   e2.onkeyup = function (){javascript:validateNumber2(i);};
                     cell2.appendChild(e2);
           
                     var cell3 = row.insertCell(3);
                     var e3 = document.createElement ("INPUT");
                     e3.setAttribute("Type", "text");
                     e3.setAttribute("size","10");
                     e3.setAttribute("name","listHakmilik["+i+"].bilanganPlot");
                     e3.setAttribute("id","bilanganPlot"+i);
                     e3.onkeyup = function (){javascript:validateNumber3(i);};
                     cell3.appendChild(e3);

                     var cell4 = row.insertCell(4);
                     var e4 = document.createElement ("INPUT");
                     e4.setAttribute("Type", "text");
                     e4.setAttribute("size","15");
                     e4.setAttribute("name","listHakmilik["+i+"].luas");
                     e4.setAttribute("id","luas"+i);
                    e4.onkeyup = function (){javascript:validateNumber4(i);};
                   cell4.appendChild(e4);
                   cell4.appendChild(document.createElement('br'));
                   var txtCell = document.createTextNode("${actionBean.hakmilik.kodUnitLuas.nama}");
                   cell4.appendChild (txtCell);
           
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}">                
                    var cell5 = row.insertCell(5);
            <c:forEach items="${actionBean.hpList}" var="mh">                   
                        var txtCell = document.createTextNode("${mh.hakmilik.idHakmilik}");
                        cell5.appendChild(txtCell);
                        cell5.appendChild(document.createElement('br'));
        </c:forEach>               
    </c:if>
    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PYTN'}">               
                   var cell5 = row.insertCell(5);
                   var sel5 = document.createElement('select');
                   sel5.name = 'idHakmilik'+ i;
                   sel5.id = 'idHakmilik'+ i;
                   sel5.options[0] = new Option('--Sila Pilih--','');
        <c:set var="j" value="1"/>
            <c:forEach items="${actionBean.hpList}" var="hp">
                       sel5.options[${j}] = new Option('${hp.hakmilik.idHakmilik}','${hp.hakmilik.idHakmilik}');
            <c:set var="j" value="${j+1}" />
        </c:forEach>
                       cell5.appendChild(sel5);
    </c:if>

                    var cell6 = row.insertCell(6);
                    var e6 = document.createElement ("INPUT");
                    e6.setAttribute("Type", "text");
                    e6.setAttribute("size","20");
                    e6.setAttribute("name","listHakmilik["+i+"].catatan");
                    cell6.appendChild(e6);
                    document.getElementById("count").value = parseInt(document.getElementById("count").value) + 1;
           
                }


                function deleteRow(tableid){
                    var rowIndex = document.getElementById("count").value;
                    if(rowIndex > 0){
                        document.getElementById(tableid).deleteRow((parseInt(rowIndex)));
                        var testCount = document.getElementById("testCount").value;
                        if(parseInt(rowIndex) == parseInt(testCount)){
            
                            $.blockUI({
                                message: $('#displayBox'),
                                css: {
                                    top:  ($(window).height() - 50) /2 + 'px',
                                    left: ($(window).width() - 50) /2 + 'px',
                                    width: '50px'
                                }
                            });
               
                            try{
                                var url = '${pageContext.request.contextPath}/pembangunan/maklumat_pecahSempadan?deleteSingle';
                                $.get(url,
                                function(data){
                                    $('#page_div').html(data);
                                    $.unblockUI();
                                },'html');

                            }catch(e){
                                alert(e);
                            }
                        }
                    }else{
                        alert("Tiada Boleh Dihapuskan");
                        return false;
                    }
                    document.getElementById("count").value = parseInt(document.getElementById("count").value)-1;
                }
    
                function saveNoRujukan(event, f){
        
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top:  ($(window).height() - 50) /2 + 'px',
                            left: ($(window).width() - 50) /2 + 'px',
                            width: '50px'
                        }
                    });          
                    var q = $(f).formSerialize();
                    var url = f.action + '?' + event;
        
                    $.post(url,q,
                    function(data){                                
                        $('#page_div',this.document).html(data);   
                        $.unblockUI();
                    },'html');           
                    return true;
                }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>           
<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatPecahSempadanActionBean">
    <s:messages />
    <s:errors />
    <s:hidden name="luas" id="luas" formatPattern="0.0000"/>
    <s:hidden name="jumluasTHakmilik" id="jumluasTHakmilik" formatPattern="0.0000"/>
    <s:hidden name="jumluasKerajaan" id="jumluasKerajaan" formatPattern="0.0000"/>
    <s:hidden name="kodUOM0" id="kodUOM0"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Plot (Pelan Tatatur Permohonan)</legend>
            <div class="content" align="center">
                <table border="0" width="80%">                    
                    <tr><td><p><label><font color="blue">Jumlah Luas : </font></label>
                                <fmt:formatNumber  pattern="#,##0.0000" value="${actionBean.luas}"/> ${actionBean.hakmilik.kodUnitLuas.nama} &nbsp;
                            </p></td></tr>
                            <c:if test="${actionBean.kodNegeri eq '04'}"> <%-- condition for Melaka --%>   
                                <c:if test="${actionBean.stageId eq 'cetaksrtdokimbaspelansediayab' || actionBean.stageId eq 'cetaksuratimbasprahitung'}">
                            <tr><td><p><label><font color="blue">No Rujukan Pelan Tatatur : </font></label>
                                        <s:text name="pelanTatatur" id="pelanTatatur" size="25" class="normal_text" maxlength="30"/>
                                    </p></td></tr>
                            <tr><td><p><label><font color="blue">No Rujukan Pelan Pra Hitungan : </font></label>
                                        <s:text name="praHitungan" id="praHitungan" size="25" class="normal_text" maxlength="30"/>
                                    </p></td></tr>
                            <tr>
                                <td align="center"> <s:button name="simpanNoRujukan" id="simpan" value="Simpan" class="btn" onclick="saveNoRujukan(this.name, this.form);"/></td>
                            </tr>
                        </c:if>
                        <c:if test="${!(actionBean.stageId eq 'cetaksrtdokimbaspelansediayab' || actionBean.stageId eq 'cetaksuratimbasprahitung')}">
                            <tr><td><p><label><font color="blue">No Rujukan Pelan Tatatur : </font></label>
                                        ${actionBean.pelanTatatur}
                                    </p></td></tr>
                            <tr><td><p><label><font color="blue">No Rujukan Pelan Pra Hitungan : </font></label>
                                        ${actionBean.praHitungan}
                                    </p></td></tr>
                                </c:if>
                            </c:if>                            
                            <c:if test="${actionBean.kodNegeri eq '05'}"> <%-- condition for NS --%>   
                                <c:if test="${edit}">
                            <tr><td><p><label><font color="blue">No Rujukan Pelan Tatatur : </font></label>
                                        <s:text name="pelanTatatur" id="pelanTatatur" size="25" class="normal_text" maxlength="30"/>
                                    </p></td></tr>
                            <tr><td><p><label><font color="blue">No Rujukan Pelan Pra Hitungan : </font></label>
                                        <s:text name="praHitungan" id="praHitungan" size="25" class="normal_text" maxlength="30"/>
                                    </p></td></tr>
                                </c:if>
                                <c:if test="${!(edit)}">
                            <tr><td><p><label><font color="blue">No Rujukan Pelan Tatatur : </font></label>
                                        ${actionBean.pelanTatatur}
                                    </p></td></tr>
                            <tr><td><p><label><font color="blue">No Rujukan Pelan Pra Hitungan : </font></label>
                                        ${actionBean.praHitungan}
                                    </p></td></tr>
                                </c:if>
                            </c:if>
                </table>
                <br>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PSMT' || actionBean.permohonan.kodUrusan.kod eq 'PSBT'}">
                    <p><b><font color="blue">Maklumat Plot Yang Diserahkan Hakmilik</font></b></p>
                </c:if>
                <c:if test="${!actionBean.permohonan.kodUrusan.kod eq 'PSMT' || !actionBean.permohonan.kodUrusan.kod eq 'PSBT'}">
                    <p><b><font color="blue">Maklumat Plot Yang Dikeluarkan Hakmilik</font></b></p>
                </c:if>

                <c:if test="${edit}">
                    <table border="0" width="80%" cellspacing="10">
                        <tr><td>
                                <table class="tablecloth" border="1" width="85%" id="tbl">
                                    <tr>
                                        <th width="17%" align="center"><b>Jenis Kategori</b></th>
                                        <th width="17%" align="center"><b>Kegunaan Tanah</b></th>
                                        <th width="17%" align="center"><b>No. Plot</b></th>
                                        <!--<th width="17%" align="center"><b>Bilangan Tingkat</b></th>-->
                                        <th width="10%" align="center"><b>Bilangan Unit</b></th>
                                        <th width="16%" align="center"><b>Luas Per-Unit</b></th>
                                        <th width="16%" align="center"><b>Hakmilik</b></th>
                                        <th width="16%" align="center"><b>Lain-lain</b></th>
                                    </tr>
                                    <c:set var="recordCount" value="0"/>
                                    <c:set var="billNo" value="0"/>
                                    <c:set var="i" value="0"/>
                                    <c:forEach items="${actionBean.listHakmilik}" var="plotPelan">
                                        <tr>
                                            <td>                                   
                                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'|| actionBean.permohonan.kodUrusan.kod eq 'SBPS'}">
                                                    <s:select name="kodKategoriTanah${i}" id="kodKategoriTanah${i}" value="${plotPelan.kategoriTanah.kod}" onchange="javascript:validateBilTingkat(${i})">
                                                        <s:option value="">--Sila Pilih--</s:option>
                                                        <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                                    </s:select>
                                                </c:if>
                                                <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'|| actionBean.permohonan.kodUrusan.kod eq 'SBPS')}">
                                                    ${actionBean.hakmilik.kategoriTanah.nama}
                                                    <s:hidden name="kodKategoriTanah${i}" value="${actionBean.hakmilik.kategoriTanah.kod}" />
                                                </c:if>

                                            </td>
                                            <td>  
                                                <%--<s:select name="kodKegunaanTanah${i}" id="kodKegunaanTanah${i}" value="${plotPelan.kegunaanTanah.kod}" style="width:430px">
                                                        <s:option value="0">--Sila Pilih--</s:option>
                                                        <s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>
                                                </s:select>--%>
                                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'|| actionBean.permohonan.kodUrusan.kod eq 'SBPS'}">
                                                    <s:select name="kodKegunaanTanah${i}" id="kodKegunaanTanah${i}" value="${plotPelan.kegunaanTanah.kod}" style="width:430px">
                                                        <s:option label="--Sila Pilih--"  value="0" />
                                                        <c:forEach items="${list.senaraiKodKategoriTanah}" var="kodKatg" >
                                                    <optgroup label="${kodKatg.nama}" />
                                                    <c:forEach items="${list.senaraiKegunaanTanah}" var="kodGuna" >
                                                        <c:if test="${kodKatg.nama eq kodGuna.kategoriTanah.nama}">
                                                            <s:option value="${kodGuna.kod}">${kodGuna.nama}</s:option>
                                                        </c:if>
                                                    </c:forEach>                                        
                                                </c:forEach>
                                            </s:select>
                                        </c:if>
                                        <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'|| actionBean.permohonan.kodUrusan.kod eq 'SBPS')}">
                                            <s:select name="kodKegunaanTanah${i}" id="kodKegunaanTanah${i}" value="${plotPelan.kegunaanTanah.kod}" style="width:430px">
                                                <s:option label="--Sila Pilih--"  value="0" />                                                                          
                                                <c:forEach items="${list.senaraiKegunaanTanah}" var="kodGuna" >
                                                    <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq kodGuna.kategoriTanah.nama}">
                                                        <s:option value="${kodGuna.kod}">${kodGuna.nama}</s:option>
                                                    </c:if>
                                                </c:forEach>                                    
                                            </s:select>
                                        </c:if>
                                </td>                                
                                <%--<td><s:text name="listHakmilik[${i}].bilanganTingkat" id="bilanganTingkat${i}" size="10" class="normal_text" onkeyup="validateNumber(this,this.value);" /></td>--%>
                                <td><s:text name="listHakmilik[${i}].noPlot" id="noPlot${i}" size="10" class="normal_text" onkeyup="validateNumber(this,this.value);"/></td>
                                <td><s:text name="listHakmilik[${i}].bilanganPlot" id="bilanganPlot${i}" size="10" class="normal_text" onkeyup="validateNumber(this,this.value);"/></td>
                                <td><s:text name="listHakmilik[${i}].luas" id="luas${i}" size="15" class="normal_text" formatPattern="0.0000" onkeyup="validateNumber(this,this.value);"/>
                                    ${actionBean.hakmilik.kodUnitLuas.nama}
                                </td>                                
                                <td>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}">
                                        <c:forEach items="${actionBean.hpList}" var="mh">
                                            ${mh.hakmilik.idHakmilik} <br/>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PYTN'}">
                                        <s:select name="idHakmilik${i}" id="idHakmilik${i}" value="${plotPelan.hakmilikPermohonan.hakmilik.idHakmilik}">
                                            <s:option value="">--Sila Pilih--</s:option>
                                            <s:options-collection collection="${actionBean.hpList}" label="hakmilik.idHakmilik" value="hakmilik.idHakmilik"/>
                                        </s:select>
                                    </c:if>        
                                </td>

                                <td><s:text name="listHakmilik[${i}].catatan" id="catatan" size="20" class="normal_text"/></td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>
                    </table>
                    </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%" border="0" cellspacing="5">
                                <tr><td width="64%" align="right"><b><font color="blue">Jumlah : </font> </b></td>
                                    <td> <fmt:formatNumber  pattern="#,##0.0000" value="${actionBean.jumlahPlotYang}"/> &nbsp; ${actionBean.hakmilik.kodUnitLuas.nama} </td>
                                </tr>
                                <tr><td width="64%" align="right"><font color="blue"><b>Baki : </b></font></td>
                                    <td> <fmt:formatNumber  pattern="#,##0.0000" value="${actionBean.baki}"/>  &nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr><!-- Hidden field for recordCount -->
                        <td><s:hidden name="count" id="count" value="${(fn:length(actionBean.listHakmilik))}" />
                            <s:hidden name="testCount" id="testCount" value="${(fn:length(actionBean.listHakmilik))}" /></td>
                    </tr>
                    <tr>
                        <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow('tbl')"/>
                            <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl')"/></td>
                    </tr>
                    <tr>
                        <td align="center"> <s:button name="simpanHakmilikPlot" id="simpan" value="Simpan" class="btn" onclick="validateSave(this.name, this.form);"/></td>
                    </tr>
                    </table>
                </c:if>
                <c:if test="${!edit}">
                    <display:table class="tablecloth" name="${actionBean.listHakmilik}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil" style="vertical-align:baseline"><c:if test="${line.pemilikan.kod eq 'H'}">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPlot}"/></c:if>
                        </display:column>
                        <display:column title="Jenis Kategori" style="vertical-align:baseline"><c:if test="${line.pemilikan.kod eq 'H'}">${line.kategoriTanah.nama}</c:if></display:column>
                        <display:column title="Kegunaan Tanah" style="vertical-align:baseline"><c:if test="${line.pemilikan.kod eq 'H'}">${line.kegunaanTanah.nama}</c:if></display:column>
                        <display:column title="No. Plot" style="vertical-align:baseline"><div align="center"> ${line.noPlot}</div></display:column>
                        <display:column title="Bilangan Unit" style="vertical-align:baseline"><c:if test="${line.pemilikan.kod eq 'H'}"><div align="center"> ${line.bilanganPlot}</div></c:if></display:column>
                        <display:column title="Luas Dipohon" style="vertical-align:baseline"><c:if test="${line.pemilikan.kod eq 'H'}"><fmt:formatNumber  pattern="#,##0.0000" value="${line.luas}"/>
                                ${line.kodUnitLuas.nama}&nbsp;</c:if></display:column>
                        <display:column title="Id Hakmilik" style="vertical-align:baseline">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}">
                                <c:forEach items="${actionBean.hpList}" var="mh">
                                    ${mh.hakmilik.idHakmilik} <br/>
                                </c:forEach>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PYTN'}">
                                ${line.hakmilikPermohonan.hakmilik.idHakmilik}
                            </c:if>
                        </display:column>

                        <c:if test="${edit2}">
                            <display:column title="Luas Diluluskan">
                                <s:text name="listNoPT1[${line_rowNum - 1}].luasDilulus" value="0.0000" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" /></display:column>
                        </c:if>
                    </display:table>
                </c:if>
                <br><br>
                <%--                <display:table class="tablecloth" name="${actionBean.listHakmilik}" cellpadding="0" cellspacing="0" id="line">
                                    <display:column title="Bil" style="vertical-align:baseline"><c:if test="${line.pemilikan.kod eq 'H'}">${line_rowNum}
                                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPlot}"/></c:if>
                                    </display:column>
                                    <display:column title="No. Plot" style="vertical-align:baseline"><c:if test="${line.pemilikan.kod eq 'H'}">${line.noPlot}</c:if></display:column>
                                    <display:column title="Luas Dipohon" style="vertical-align:baseline"><c:if test="${line.pemilikan.kod eq 'H'}"><fmt:formatNumber  pattern="#,##0.0000" value="${line.luas}"/>
                                            ${line.kodUnitLuas.nama}&nbsp;</c:if></display:column>
                                    <display:column title="Id Hakmilik" style="vertical-align:baseline">${line.hakmilikPermohonan.hakmilik.idHakmilik}</display:column>

                    <c:if test="${edit2}">
                           <display:column title="Luas Diluluskan">
                               <s:text name="listNoPT1[${line_rowNum - 1}].luasDilulus" value="0.0000" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" /></display:column>
                    </c:if>

                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <div align="center">
                            <a href="#" src='${pageContext.request.contextPath}/images/edit.gif' onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;">Kemaskini</a>
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}','KHK');return false;">
                            </div>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="remove('${line.idPlot}')">
                            </div>
                        </display:column>
                    </c:if>
                </display:table>
                <c:if test="${edit}">
                    <p>
                        <s:button name="tambahHakmilik" class="btn" value="Tambah" onclick="addNewHakmilik();" id="btnH" />onmouseover="checkPlot();"
                    </p>
                </c:if>
                    <br>
                --%>
                <s:hidden name="bilplotHakmiliktemp" id="hakmilik"/>
                <s:hidden name="bilplotTHakmiliktemp" id="thakmilik"/>
                <s:hidden name="bilplotKerajaantemp" id="kerajaan"/>
            </div>
        </fieldset>
    </div>

</s:form>
