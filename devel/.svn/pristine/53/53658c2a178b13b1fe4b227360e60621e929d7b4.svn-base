<%-- 
    Document   : penyatuanPetak
    Created on : Dec 23, 2010, 12:31:13 PM
    Author     : NageswaraRao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function validateNumber1(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
          //  addRow();
            return;
        }else{
          addRow();
        }
    }


    function removeNonNumeric( strString )
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


     function addRow(){
        var table = document.getElementById('tbl');
        var count=document.getElementById('bilanganPetakBaru').value;
       // alert(table);
       // alert(count);
        var rowcount = table.rows.length;
        //alert(rowcount);
        for(var i=rowcount;i>1;i--){
            table.deleteRow(i-1);
        }

        for(var i=1;i<=count;i++){
            var rowcount = table.rows.length;
            var row = table.insertRow(rowcount);

            var cell1 = row.insertCell(0);
            var e1 = document.createElement ("INPUT");
            e1.setAttribute("Type", "text");
            e1.setAttribute("size","10");
            e1.setAttribute("name","petakBaru"+i+"1");
            e1.setAttribute("id","petakBaru"+i+"1");
            //e1.setAttribute("value",i);
            cell1.appendChild(e1);

            var cell2 = row.insertCell (1);
            var e2 = document.createElement ("INPUT");
            e2.setAttribute("Type", "text");
            e2.setAttribute("size","40");
            e2.setAttribute("name","petakBaru"+i+"2");
            e2.setAttribute("id","petakBaru"+i+"2");
            cell2.appendChild (e2);

            var cell3 = row.insertCell(2);
            var e3 = document.createElement("INPUT");
            e3.setAttribute("type","text");
            e3.setAttribute("size","40");
            e3.setAttribute("name","petakBaru"+i+"3");
            e3.setAttribute("id","petakBaru"+i+"3");
            cell3.appendChild(e3);

            var cell4 = row.insertCell(3);
            var e4 = document.createElement("INPUT");
            e4.setAttribute("type","text");
            e4.setAttribute("size","40");
            e4.setAttribute("name","petakBaru"+i+"4");
            e4.setAttribute("id","petakBaru"+i+"4");
            cell4.appendChild(e4);

        }
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form  name="form1" beanclass="etanah.view.strata.PenyatuanPetakActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Hakmilik</legend>
             <p>
                 <s:hidden name="bilanganPetakBaru" id="bilanganPetakBaru"  onkeyup="validateNumber1(this,this.value);"/>
                 
            </p>
            <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="line">
                <s:text name="idHakmilik" id="idHakmilik" value="${line.hakmilik.idHakmilik}" size="20" readonly="readonly"/>
            </c:forEach>

            <table  width="80%" id="tbl" class="tablecloth" border="1">
                 <tr>
                     <th  width="10%" align="center"><b>Bil</b></th>
                     <th  width="30%" align="center"><b>Nombor Hakmilik</b></th>
                     <th  width="30%" align="center"><b>Luas Petak(meter persegi)</b></th>
                     <th  width="30%" align="center"><b>Unit Syer</b></th>
                 </tr>
                 <c:set value="0" var="i"/>
                 <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="line">
                <tr style="font-weight:bold">
                    <td align="center"><c:out value="${i+1}"/></td>
                    <td><s:text name="idHakmilik${i}" id="idHakmilik${i}" readonly="readonly" value="${line.hakmilik.idHakmilik}" size="35"/></td>
                    <td><s:text name="luas${i}" id="luas${i}" readonly="readonly" value="${line.hakmilik.luas}"  size="35" /></td>
                    <td><s:text name="unitSyer${i}" id="unitSyer${i}" readonly="readonly" value="${line.hakmilik.unitSyer}" size="35"/></td>
                </tr>
                    <c:set var="i" value="${i+1}" />
                </c:forEach>
                 <%--<c:set var="recordCount" value="0"/>
                 <c:set var="billNo" value="0"/>
                  <c:forEach items="${actionBean.senaraiKelulusan}" var="senarai">
                       <c:set var="recordCount" value="${recordCount+1}"/>
                             <c:if test="${(recordCount-1)%4 eq 0}">
                                 <c:set var="billNo" value="${billNo+1}"/>
                               <tr>
                                <td style="display:none">${senarai.idKandungan}</td>
                                <td>${billNo} </td>
                              </c:if>
                                  <td width="22%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="35"/> </td>
                  </c:forEach>--%>
             </table>
            
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>Maklumat Penyatuan Petak</legend>
            <p>
                <label>Bilangan Petak Baru: </label>
                <s:text name="bilanganPetakBaru" id="bilanganPetakBaru"  onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Luas Petak(meter persegi) : </label>
                <s:text name="luasPetak" id="luasPetak" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Unit Syer : </label>
                <s:text name="unitSyer" id="unitSyer" onkeyup="validateNumber(this,this.value);"/>
            </p>
        </fieldset><br>
        
         <fieldset class="aras1">
            <legend>Maklumat Harta Bersama </legend>
            <p>
                <label>Luas(meter persegi) : </label>
                <s:text name="luas1" id="luas1" onkeyup="validateNumber(this,this.value);" />
            </p>
            <p>
                <label>Jumlah Syer :</label>
                <s:text name="jumlahSyer"  id="jumlahSyer" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label> Perihal Harta Bersama:</label>
                <s:textarea  name="hartaBersama" id="hartaBersama" rows="5" cols="50" />
            </p>
        </fieldset>

            <p>
                <br>
                <label>&nbsp;</label>
                <s:button name="simpanMaklumatBangunan" id="simpan" value="Simpan" class="btn" />
                <%--<s:button name="simpanMaklumatBangunan" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>--%>
           </p>
       
    </div>

</s:form>

