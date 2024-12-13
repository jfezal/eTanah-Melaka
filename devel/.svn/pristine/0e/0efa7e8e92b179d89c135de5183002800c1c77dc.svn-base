<%-- 
    Document   : maklumat_pecahSempadan_tukarSyarat
    Created on : Aug 2, 2011, 3:20:10 PM
    Author     : NageswaraRao
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
<script type="text/javascript">

    function validateSave(event, f){
        if(validateLuas()&& validateHakmilik()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',this.document).html(data);
            },'html');
          return true;
        }
    }

    function validateHakmilik(){       
        var rowCount = document.getElementById("count").value;
        for(var i=0;i<rowCount;i++){
            if(document.getElementById("idHakmilik"+i).value == ""){
               alert('Sila Pilih ID Hakmilik.');
                $('#idHakmilik'+i).focus();
                return false;
            }
        }
        return true;
    
    }


    function validateLuas(){
        var jumlahLuas = document.getElementById("luas").value;
        var rowCount = document.getElementById("count").value;
        var sum=0;       
        for(var i=0;i<rowCount;i++){
            <%--sum = (sum + ((parseFloat(Number(document.getElementById("luas"+i).value)))*(parseFloat(Number(document.getElementById("bilanganPlot"+i).value))))).toFixed(2);--%>
           sum = (sum + ((parseFloat(Number(document.getElementById("luas"+i).value)))*(parseFloat(Number(document.getElementById("bilanganPlot"+i).value)))));
        }        
        <%--sum = sum.toFixed(2);--%>
        alert("jumlahLuas:"+jumlahLuas);
        alert("sum:"+sum);
        <%--sum=(Math.round(sum*10000)/10000).toFixed(4);--%>
        <%--alert("sum:"+sum);--%>
            if(parseFloat(Number(jumlahLuas)) < parseFloat(Number(sum))){
                alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                return false;
            }
            else if(parseFloat(Number(sum)) < parseFloat(Number(jumlahLuas)) ){
                alert('Luas yang dimasukkan kurang daripada Jumlah Luas Keseluruhan yang disediakan.');
                return false;
            }
        return true;
    }

    function validation(index){
        <%--validateNumber3(index);
        var jumlahLuas = document.getElementById("luas").value;
        var rowCount = document.getElementById("count").value;
        var sum=0;        
        for(var i=0;i<rowCount;i++){
            sum = (sum + ((parseFloat(Number(document.getElementById("luas"+i).value)))*(parseFloat(Number(document.getElementById("bilanganPlot"+i).value))))).toFixed(2);
            sum = (sum + ((parseFloat(Number(document.getElementById("luas"+i).value)))*(parseFloat(Number(document.getElementById("bilanganPlot"+i).value)))));
        } --%>
        <%--sum=(Math.round(sum*10000)/10000).toFixed(4);--%>
            <%--if(parseFloat(Number(jumlahLuas)) < parseFloat(Number(sum))){
                alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                var temp = $('#luas'+index).val();
                temp = temp.substr(0,temp.length-1);
                alert(temp);
                $('#luas'+index).val(temp);
                $('#luas'+index).focus();
                return true;
            }--%>
    }

    function addRow(tableid){
           var table = document.getElementById(tableid);
           var rowcount = table.rows.length;
           var row = table.insertRow(rowcount);
           var i = parseInt(rowcount)-1;
           
           var cell0 = row.insertCell(0);
           var sel0 = document.createElement('select');
           sel0.name = 'kodKategoriTanah'+ i;           
           sel0.options[0] = new Option('--Sila Pilih--','');
           <c:set value="1" var="j" />
           <c:forEach items="${list.senaraiKodKategoriTanah}" var="kodKatg">
               sel0.options[${j}] = new Option('${kodKatg.nama}','${kodKatg.kod}');
               <c:set value="${j+1}" var="j" />
           </c:forEach>
           cell0.appendChild(sel0);

           var cell1 = row.insertCell(1);
           var sel1 = document.createElement('select');
           sel1.name = 'kodKegunaanTanah'+ i;
           sel1.style.width = '430px';
           sel1.options[0] = new Option('--Sila Pilih--','');
           <c:set var="j" value="1"/>
           <c:forEach items="${list.senaraiKegunaanTanah}" var="kodKatg">
               sel1.options[${j}] = new Option('${kodKatg.nama}','${kodKatg.kod}');
               <c:set var="j" value="${j+1}" />
           </c:forEach>
           cell1.appendChild(sel1);

           var cell2 = row.insertCell(2);
           var e2 = document.createElement ("INPUT");
           e2.setAttribute("Type", "text");
           e2.setAttribute("size","10");
           e2.setAttribute("name","senaraiPermohonanPlotPelan["+i+"].bilanganTingkat");
           e2.setAttribute("id","bilanganTingkat"+i);
           e2.onkeyup = function (){javascript:validateNumber(i);};
           cell2.appendChild(e2);

           var cell3 = row.insertCell(3);
           var e3 = document.createElement ("INPUT");
           e3.setAttribute("Type", "text");
           e3.setAttribute("size","10");
           e3.setAttribute("name","senaraiPermohonanPlotPelan["+i+"].bilanganPlot");
           e3.setAttribute("id","bilanganPlot"+i);
           e3.onkeyup = function (){javascript:validateNumber2(i);};
           cell3.appendChild(e3);

           var cell4 = row.insertCell(4);
           var e4 = document.createElement ("INPUT");
           e4.setAttribute("Type", "text");
           e4.setAttribute("size","15");
           e4.setAttribute("name","senaraiPermohonanPlotPelan["+i+"].luas");
           e4.setAttribute("id","luas"+i);
           e4.onkeyup = function (){javascript:validation(i);};
           cell4.appendChild(e4);

           cell4.appendChild(document.createElement('br'));
           var txtCell = document.createTextNode("${actionBean.hakmilik.kodUnitLuas.nama}");
           cell4.appendChild (txtCell);

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
           
           var cell6 = row.insertCell(6);
           var e6 = document.createElement ("INPUT");
           e6.setAttribute("Type", "text");
           e6.setAttribute("size","20");
           e6.setAttribute("name","senaraiPermohonanPlotPelan["+i+"].catatan");
           cell6.appendChild(e6);

           document.getElementById("count").value = parseInt(document.getElementById("count").value) + 1;
    }

    function deleteRow(tableid){
        var rowIndex = document.getElementById("count").value;
        <%--alert("rowIndex:"+rowIndex);--%>
       if(rowIndex > 0){
         document.getElementById("count").value = parseInt(document.getElementById("count").value)-1;
         document.getElementById(tableid).deleteRow((parseInt(rowIndex)));
         var testCount = document.getElementById("testCount").value;
        if(parseInt(rowIndex) <= parseInt(testCount)){

            <%--alert("javacall");--%>
           try{
           var url = '${pageContext.request.contextPath}/pembangunan/melaka/maklumat_pecahSempadan_tukarSyarat?deleteSingle';
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


    function validateNumber(index) {
        //if it is character, then remove it..
        var content = document.getElementById("bilanganTingkat"+index).value;
        if (isNaN(content)) {
            document.getElementById("bilanganTingkat"+index).value = RemoveNonNumeric(content);
            return;
        }
    }

      function validateNumber2(index) {
        //if it is character, then remove it..
        var content = document.getElementById("bilanganPlot"+index).value;
        if (isNaN(content)) {
            document.getElementById("bilanganPlot"+index).value = RemoveNonNumeric(content);
            return;
        }
    }

     function validateNumber3(index) {
        //if it is character, then remove it..
        var content = document.getElementById("luas"+index).value;
        if (isNaN(content)) {
            document.getElementById("luas"+index).value = RemoveNonNumeric(content);
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


</script>

<stripes:form beanclass="etanah.view.stripes.pembangunan.MaklumatPecahSempadanTukarSyaratActionBean">
<s:messages/>
<s:hidden name="luas" id="luas" formatPattern="0.0000"/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Pecah Sempadan Tukar Syarat
        </legend>
        <div class="content" align="center">
            <table border="0" width="80%" cellspacing="10">
            <tr><td><b>Cadangan Pembangunan ke atas tanah di atas adalah seperti berikut :- </b></td></tr>
             <tr><td><p><label><font color="blue">Jumlah Unit : </font></label>
                         <b> <fmt:formatNumber pattern="#,##0.0000" value="${actionBean.luas}"/> ${actionBean.hakmilik.kodUnitLuas.nama}</b>
                            </p></td></tr>
                <%--<c:if test="${edit}">--%>
                <tr><td>
                        <table class="tablecloth" border="1" width="85%" id="tbl">
                            <tr>
                                <th width="17%" align="center"><b>Jenis Kategori</b></th>
                                <th width="17%" align="center"><b>Kegunaan Tanah</b></th>
                                <th width="17%" align="center"><b>Bilangan Tingkat</b></th>
                                <th width="10%" align="center"><b>Bilangan Unit</b></th>
                                <th width="16%" align="center"><b>Luas</b></th>
                                <th width="16%" align="center"><b>Hakmilik</b></th>
                                <th width="16%" align="center"><b>Lain-lain</b></th>
                            </tr>
                            <c:set var="recordCount" value="0"/>
                            <c:set var="billNo" value="0"/>
                            <c:set var="i" value="0"/>
                            <c:forEach items="${actionBean.senaraiPermohonanPlotPelan}" var="plotPelan">
                            <tr>
                                <td>                                    
                                    <s:select name="kodKategoriTanah${i}" value="${plotPelan.kategoriTanah.kod}" style="text-transform:uppercase" >
                                        <s:option value="">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                                <td>
                                    <s:select name="kodKegunaanTanah${i}" value="${plotPelan.kegunaanTanah.kod}" style="text-transform:uppercase;width:430px">
                                        <s:option value="0">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                                <td><s:text name="senaraiPermohonanPlotPelan[${i}].bilanganTingkat" id="bilanganTingkat${i}" size="10" class="normal_text" onkeyup="validateNumber(${i});" /></td>
                                <td><s:text name="senaraiPermohonanPlotPelan[${i}].bilanganPlot" id="bilanganPlot${i}" size="10" class="normal_text" onkeyup="validateNumber2(${i});"/></td>
                                <td><s:text name="senaraiPermohonanPlotPelan[${i}].luas" id="luas${i}" size="15" class="normal_text" formatPattern="0.0000" onkeyup="validation(${i});"/>
                                    ${actionBean.hakmilik.kodUnitLuas.nama}                                    
                                </td>
                                <td>
                                    <s:select name="idHakmilik${i}" id="idHakmilik${i}" value="${plotPelan.hakmilikPermohonan.hakmilik.idHakmilik}">
                                        <s:option value="">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${actionBean.hpList}" label="hakmilik.idHakmilik" value="hakmilik.idHakmilik"/>
                                    </s:select>
                                </td>
                                <td><s:text name="senaraiPermohonanPlotPelan[${i}].catatan" id="catatan" size="20" class="normal_text"/></td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                            </c:forEach>
                        </table>
                    </td>
                </tr>
                <tr><!-- Hidden field for recordCount -->
                    <td><s:hidden name="count" id="count" value="${(fn:length(actionBean.senaraiPermohonanPlotPelan))}" />
                        <s:hidden name="testCount" id="testCount" value="${(fn:length(actionBean.senaraiPermohonanPlotPelan))}" /></td>
                </tr>
                <tr>
                    <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow('tbl')"/>
                        <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl')"/></td>
                </tr>
                <%--</c:if>--%>
            </table>
                
                <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick="validateSave(this.name, this.form);"/>
            </div>
        </fieldset>
    </div>

</stripes:form>

