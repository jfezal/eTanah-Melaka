<%-- 
    Document   : SuratKelulusanTukarSyarat
    Created on : Sep 19, 2013, 10:25:14 PM
    Author     : khairul.hazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
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
</script>


<s:form beanclass="etanah.view.stripes.pembangunan.SuratKelulusanTukarSyaratActionBean">
    <s:messages/>
    <s:errors/>
    
<div class="subtitle">
   <fieldset class="aras1">
       <legend></legend>
            <div class="content" align="center">
                                      
                        <table border="0" width="85%" align="center" cellspacing="10">
                            <tr>
                                <td align="center"><b>Keputusan Pengarah Tanah Dan Galian Untuk ${actionBean.permohonan.kodUrusan.nama} </b></td>
                            </tr>
                        </table>
                    
                    <br/>
                   
                    <table border="0" width="85%" align="center" cellspacing="10">
                    <c:if test = "${actionBean.permohonan.keputusan.kod ne null}">
                    <c:if test = "${actionBean.permohonan.keputusan.kod eq 'L'}">
                    <tr>                       
                        <td align ="center"> Dimaklumkan bahawa permohonan tersebut telah diluluskan dengan syarat-syarat seperti di Lampiran A.</td>                       
                    </tr>
                    </c:if>
                    
                    <c:if test = "${actionBean.permohonan.keputusan.kod eq 'T'}">
                    <tr>
                        <td align ="center"> Dimaklumkan bahawa permohonan tersebut tidak diluluskan oleh Pengarah Tanah dan Galian Melaka berdasarkan
                        <s:textarea rows="5" cols="100" name="sebabPenolakan" class="normal_text"/></td>
                    </tr>
                    <tr>
                        <td align ="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </td>
                    </tr>
                    </c:if>
                    </c:if>
                    </table>                               
            </div>
       </fieldset>
    </div>
</s:form>