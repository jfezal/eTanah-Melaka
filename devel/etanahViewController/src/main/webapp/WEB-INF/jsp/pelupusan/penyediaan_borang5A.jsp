<%-- 
    Document   : penyediaan_borang5A
    Created on : May 20, 2010, 10:52:22 AM
    Author     : nurul.izza
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


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

    function calc(value){
        var jum = $("#jumlah").val();
        var jumlah = parseFloat(Number(value)) + parseFloat(Number(jum));
        $("#jumlah").val(jumlah);
        <%--$("#jumlah1").val(jumlah);--%>
    }

    function calc2(){
        $("#jumlah").val("0.00");
    }
    
</script>
        <s:form beanclass="etanah.view.stripes.pelupusan.Borang5AActionBean" name="page_div" id="page_div">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                      Maklumat Borang 5A
                    </legend>
                    <div class="content" align="center">
                        <table border="0" width="80%">
                            <c:if test="${edit}">
                            <tr><td><p><label id="permohonantuntutkos.item"><font color="black">Cukai Tahun Pertama :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text id="cukai" name="permohonantuntutkos.amaunTuntutan" onchange="calc(this.value)"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                            </c:if>
                             <c:if test="${edit}">
                             <tr><td><p><label id="permohonantuntutkos.item"><font color="black">Premium :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text id="primium" name="permohonantuntutkos.amaunTuntutan" onchange="calc(this.value);" value="0.00"
                                        onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;" /></p></td></tr><br>
                             </c:if>
                             <c:if test="${edit}">
                               <tr><td><p><label id="permohonantuntutkos.item"><font color="black">Upah Ukur/Tanda Sempadan :</font></label>
                               &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text id="upah" name="permohonantuntutkos.amaunTuntutan" onchange="calc(this.value);" value="0.00"
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                             </c:if>

                             <c:if test="${edit}">
                               <tr><td><p><label id="permohonantuntutkos.item"><font color="black">Kos Sumbangan Infrastruktur :</font></label>
                               &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text id="sumbangan" name="permohonantuntutkos.amaunTuntutan" onchange="calc(this.value);" value="0.00"
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                             </c:if>

                             <c:if test="${edit}">
                               <tr><td><p><label id="permohonantuntutkos.item"><font color="black">Penyediaan Pelan Suratan Hakmilik Tetap :</font></label>
                               &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text id="suratanTetap" name="permohonantuntutkos.amaunTuntutan" onchange="calc(this.value);" value="0.00"
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;" /></p></td></tr><br>
                             </c:if>
                            
                            
                             <c:if test="${edit}">
                               <tr><td><p><label id="permohonantuntutkos.item"><font color="black">Pendaftaran Suratan Hakmilik Tetap :</font></label>
                               &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text id="suratanHakmilikTetap" name="permohonantuntutkos.amaunTuntutan" onchange="calc(this.value);" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                             </c:if>
                               
                             <c:if test="${edit}">
                               <tr><td><p><label id="permohonantuntutkos.item"><font color="black">Penyediaan Pelan Suratan Hakmilik Sementara :</font></label>
                               &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="suratanSementara" onchange="calc(this.value);" value="0.00"
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                             </c:if>

                             <c:if test="${edit}">
                               <tr><td><p><label id="permohonantuntutkos.item"><font color="black">Pendaftaran Suratan Hakmilik Sementara :</font></label>
                               &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="pendaftaranTetap" onchange="calc(this.value);" value="0.00"
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                             </c:if>

                             <c:if test="${edit}">
                               <tr><td><p><label id="permohonantuntutkos.item"><font color="black">Notis :</font></label>
                               &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="notis" onchange="calc(this.value);" value="0.00"
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                             </c:if>

                             <c:if test="${edit}">
                               <tr><td><p><label id="permohonantuntutkos.item"><font color="black">Jumlah :</font></label>
                                           &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="jumlah" id="jumlah" disabled="true"
                                            onchange="calc(this.value);" value="0.00" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                             </c:if>      
                       
                        </table>
                    </div>
                </fieldset>
        </div>
        <br>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit('page_div',this.form, this.name)"/>&nbsp;
                </p>

</s:form>


