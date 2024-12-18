<%-- 
    Document   : listKodCawangan
    Created on : Jan 12, 2012, 3:13:04 PM
    Author     : Aziz
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<div class= "all">
    <script type="text/javascript">
     maximizeWindow();
        //     $(document).ready(function(){
        //        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        //    });

        
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
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
    
        function kemaskini(frm, value,value2) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodCawangan?viewKodCawangan&kod='+value+'&table_kod='+value2;
            frm.action = url;
            frm.submit();
        }
         function goBack(frm) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodRujukan?findKodRuj';
            frm.action = url;
            frm.submit();
        }
         function addDokumen(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodCawangan?viewAddForm&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.KodCawanganActionBean" name ="kodCawangan" id ="kodCawangan">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Kod Cawangan</legend>
                <p><label><font color="red">*</font>Kod Daerah:</label>
                         <s:select name="daerah" id="daerah" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p><label><font color="red">*</font>Cawangan Utama</label>
                         <s:select name="cawanganUtama" id="cawanganUtama" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodCawangan}" label="name" value="kod" />
                    </s:select>
                </p>
                 <s:hidden value = "${actionBean.nameTable}" name = "nameTable"/>
                <p><label><font color="red">*</font>Nama :</label>
                            <s:text id="nama" name="nama"   value="${actionBean.nama}"></s:text>
                </p>
                 <p><label><font color="red">*</font>Kod PTJ :</label>
                            <s:text id="kodptj" name="kodptj"   value="${actionBean.kodptj}"></s:text>
                </p>
                 <p><label><font color="red">*</font>Kod Jabatan SPEK :</label>
                            <s:text id="kodSpek" name="kodSpek"   value="${actionBean.kodSpek}"></s:text>
                </p>
                <p align="center"><s:submit name="KodList2" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('kodCawangan')" />&nbsp;<s:button name = "tambah" class="btn" value ="Tambah" onclick= "addDokumen(document.forms.kodCawangan,'${actionBean.nameTable}')" />&nbsp;<s:button name="kembali" value="Kembali" class="btn" onclick="goBack(document.forms.kodCawangan)" /></p>
         
            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">   
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Kod Cawangan
                    </legend>
                    <display:table  name="${actionBean.listKodCawangan}" id="line"  class="tablecloth" pagesize="10" requestURI="${pageContext.request.contextPath}/utility_kod/kodCawangan">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                        <display:column property ="daerah.kod" sortable="true" title="Kod Daerah" style = "verticle-align:baseline" ></display:column>
                         <display:column property ="cawanganUtama.kod" sortable="true" title="Kod Cawangan Utama" style = "verticle-align:baseline" ></display:column>
                          <display:column property ="name" sortable="true" title="Nama" style = "verticle-align:baseline" ></display:column>
                         <display:column property ="kodPTJ" sortable="true" title="Kod PTJ" style = "verticle-align:baseline" ></display:column>
                         <display:column property ="kodJabatanSpek" sortable="true" title="Kod Jabatan SPEK" style = "verticle-align:baseline" ></display:column>
                         
                        
                   <display:column title="Tindakan">
                            <div align ="center">
                                <img alt='Klik untuk pilih jadual' border='0' src='${pageContext.request.contextPath}/images/view_icon.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="kemaskini(document.forms.kodCawangan,'${line.kod}','${actionBean.nameTable}')" onmouseover="this.style.cursor='pointer';"></img>
                            </div>
                        </display:column>
                    </display:table>
                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>



