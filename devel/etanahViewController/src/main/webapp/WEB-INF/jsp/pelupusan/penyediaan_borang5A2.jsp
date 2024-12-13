<%-- 
    Document   : penyediaan_borang5A
    Created on : May 20, 2010, 10:52:22 AM
    Author     : nurul.izza
    modify by Siti Fariza Hanim
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
        <%--alert("===ddddd");--%>
        var jum = $("#jumlah").val();
        var jumlah = parseFloat(Number(value)) + parseFloat(Number(jum));
        $("#jumlah").val(jumlah);
        <%--$("#jumlah1").val(jumlah);--%>
    }

    function calc2(){
        $("#jumlah").val("0.00");
    }
   function test(f)
    {
        $(f).clearForm();
    }

</script>

   <s:form beanclass="etanah.view.stripes.pelupusan.Penyediaan_borang5AActionBean">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                      Maklumat Borang 5A (Seksyen 81 dan 82)

                    </legend>
                    <div class="content"><br/>
                            <c:if test="${edit}">
                             <p><label id="permohonantuntutkos.item">Cukai Tahun Pertama :</label>
                                <s:text id="Premium" name="amaunTuntutan0" onchange="calc(this.value);" value=""
                                        onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:150px;" /></p><br>
                             </c:if>
                             <c:if test="${edit}" >
                               <p><label id="permohonantuntutkos.item">Premium :</label>
                               <s:text id="notis" name="amaunTuntutan1" onchange="calc(this.value);" value=""
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:150px;"/></p><br>
                            </c:if>
                            <c:if test="${edit}">
                            <p><label id="permohonantuntutkos.item">Upah Ukur/Tanda Sempadan :</label>
                                <s:text id="cukai" name="amaunTuntutan2" onchange="calc(this.value)"
                                        value="" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:150px;"/></p><br>
                            </c:if>
                            <c:if test="${edit}">
                            <p><label id="permohonantuntutkos.item">Kos Sumbangan Infrastruktur :</label>
                                <s:text id="cukai" name="amaunTuntutan3" onchange="calc(this.value)"
                                        value="" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:150px;"/></p><br>
                            </c:if>
                            <c:if test="${edit}">
                               <p><label id="permohonantuntutkos.item">Penyediaan Pelan Suratan Hakmilik Tetap :</label>
                               <s:text id="suratanTetap" name="amaunTuntutan4" onchange="calc(this.value);" value=""
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:150px;" /></p><br>
                            </c:if>
                             <c:if test="${edit}">
                               <p><label id="permohonantuntutkos.item">Pendaftaran Suratan Hakmilik Tetap :</label>
                               <s:text id="upah" name="amaunTuntutan5" onchange="calc(this.value);" value=""
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:150px;"/></p><br>
                             </c:if>

                             <c:if test="${edit}">
                               <p><label id="permohonantuntutkos.item">Penyediaan Pelan Suratan Hakmilik Sementara :</label>
                               <s:text id="sumbangan" name="amaunTuntutan6" onchange="calc(this.value);" value=""
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:150px;"/></p><br>
                             </c:if>

                             <c:if test="${edit}">
                               <p><label id="permohonantuntutkos.item"> Pendaftaran Suratan Hakmilik Sementara :</label>
                               <s:text id="suratanHakmilikTetap" name="amaunTuntutan7" onchange="calc(this.value);" value="" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:150px;"/></p><br>
                             </c:if>

                             <c:if test="${edit}">
                               <p><label id="permohonantuntutkos.item">Notis :</label>
                               <s:text id="suratanSementara" name="amaunTuntutan8" onchange="calc(this.value);" value=""
                                       onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:150px;"/></p><br>
                             </c:if>

                            <c:if test="${edit}">
                               <p><label id="permohonantuntutkos.item"><font color="black">Jumlah :</font></label>
                               <s:text name="jumlah" id="jumlah" disabled="true"
                                onchange="calc(this.value);" value="0.00" formatPattern="#,##0.00" style="width:150px;"/></p><br>
                             </c:if>

                    </div>
                </fieldset>
        </div>
        <br>
                <p>
                    <label>&nbsp;</label>

                    <s:button name="simpans" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>&nbsp;
                    <s:button name="reset" value="Isi Semula" onclick="return test();" class="btn"/>
                 </p>

</s:form>

