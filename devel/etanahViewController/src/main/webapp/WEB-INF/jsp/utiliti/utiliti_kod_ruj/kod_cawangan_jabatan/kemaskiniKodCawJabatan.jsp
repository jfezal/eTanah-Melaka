<%-- 
    Document   : kemaskiniKodAgensi
    Created on : Jan 13, 2012, 6:28:45 PM
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
         function goTo(frm, value,value2) {
           if(confirm("Adakah anda pasti untuk kemaskini data?")){
           var url = '${pageContext.request.contextPath}/utility_kod/kodCawanganJabatan?updateData&table_name='+value+'&kod='+value2;
            frm.action = url;
            frm.submit();
            }
        }
         function goTo2(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodCawanganJabatan?KodList&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.KodCawanganJabatanActionBean" name ="kemaskiniKodCawJabatan" id ="kemaskiniKodCawJabatan">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Kemaskini Kod Cawangan Jabatan </legend>
                 <p><label><font color="red">*</font>Id Rekod    :</label>${actionBean.details.kod}
                </p>
                <p><label><font color="red">*</font>Kod Cawangan</label>
                         <s:select name="details.cawangan.kod" id="kodCawangan" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodCawangan}" label="name" value="kod" />
                    </s:select>
                </p>
                 <p><label><font color="red">*</font>Nama :</label>
                     <s:text id="nama" name="details.nama" value ="${actionBean.details.nama}" size="70"></s:text>
                </p>
                  <p><label><font color="red">*</font>Alamat 1 :</label>
                            <s:text id="alamat1" name="details.alamat1" value ="${actionBean.details.alamat1}"size="70"></s:text>
                </p>
                <p><label><font color="red">*</font>Alamat 2 :</label>
                            <s:text id="alamat2" name="details.alamat2" value ="${actionBean.details.alamat2}"size="70"></s:text>
                </p>
                <p><label><font color="red">*</font>Alamat 3 :</label>
                            <s:text id="alamat3" name="details.alamat3" value ="${actionBean.details.alamat3}"size="70"></s:text>
                </p>
                <p><label><font color="red">*</font>Alamat 4 :</label>
                            <s:text id="alamat4" name="details.alamat4" value ="${actionBean.details.alamat4}"size="70"></s:text>
                </p>
                <p><label><font color="red">*</font>Poskod:</label>
                            <s:text id="poskod" name="details.poskod" value ="${actionBean.details.poskod}"></s:text>
                </p>
                 <p><label><font color="red">*</font>Kod Negeri:</label>
                         <s:select name="details.negeri.kod" id="negeri" >
                                <s:option value="00">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p>
                 <p><label><font color="red">*</font>Kod jabatan:</label>
                         <s:select name="details.jabatan.kod" id="kodJabatan" >
                                <s:option value="TD">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJabatan}" label="nama" value="kod" />
                    </s:select>
                </p>
                <s:hidden value = "${actionBean.nameTable}" name = "namatable"/>
                <s:hidden value = "${actionBean.kod}" name = "kod"/>
                
                 <p>
                        <label>Aktif  :</label>
                        <s:radio name="details.aktif" value="Y"></s:radio> Aktif
                        <s:radio name="details.aktif" value="T" ></s:radio> Tidak
                    </p>
                     <p><label><font color="red">*</font>No Telefon 1 :</label>
                            <s:text id="poskod" name="details.noTelefon1" value ="${actionBean.details.noTelefon1}"></s:text>
                </p>
                 <p><label><font color="red">*</font>No Telefon 1 :</label>
                            <s:text id="poskod" name="details.noTelefon1" value ="${actionBean.details.noTelefon1}"></s:text>
                </p>
               <p><label><font color="red">*</font>Dimasukkan Oleh    :</label>${actionBean.details.infoAudit.dimasukOleh.idPengguna}
                </p>
                   <p><label><font color="red">*</font>Tarikh Kemasukan    :</label>${actionBean.details.infoAudit.tarikhMasuk}
                </p>
                <p><label><font color="red">*</font>DiKemaskini Oleh    :</label>${actionBean.details.infoAudit.dikemaskiniOleh.idPengguna}
                </p>
                   <p align="left"><label><font color="red">*</font>Tarikh Kemaskini    :</label>${actionBean.details.infoAudit.tarikhKemaskini}
                </p>
                 <br/><br/><br/>
                 <p align="center"><s:submit name="updateData" onclick="goTo(document.forms.kemaskiniKodCawJabatan,'${actionBean.nameTable}','${actionBean.kod}')" value="Simpan" class="btn"/>&nbsp;<s:button name ="kembali" class="btn" value ="Kembali" onclick="goTo2(document.forms.kemaskiniKodCawJabatan,'${actionBean.nameTable}')"/></p>
         
            </fieldset>
        </div>
       
    </s:form>
</div>


