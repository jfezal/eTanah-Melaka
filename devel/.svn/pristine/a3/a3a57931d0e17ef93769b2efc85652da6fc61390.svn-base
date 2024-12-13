<%-- 
    Document   : keputusanRPP
    Created on : Jun 21, 2012, 3:31:32 PM
    Author     : NageswaraRao
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    $(document).ready( function(){
        if($('#ulasan').val() == 'N'){
            $('#nominal').show() ;
            $('#bukanNominal').hide() ;
        }
        else {
            $('#nominal').hide() ;
            $('#bukanNominal').show() ;

        }
        if( ${actionBean.permohonan.catatan eq 'PR'}){
            $('#tempohBulanDiv').hide() ;
            if(${actionBean.permohonan.tempohBulan ne ''}){
                document.getElementById('tempohBulan').value = '';
            }
            document.getElementById('tempohBulanDiv').style.visibility = "hidden";
        }else{
            $('#tempohBulanDiv').show() ;
            document.getElementById('tempohBulanDiv').style.visibility = "visible";
        }

    });

    function forNominal(){
        //        alert($('#ulasan').val());
        if($('#ulasan').val() == 'N'){
            $('#nominal').show() ;
            $('#bukanNominal').hide() ;
            $('#nilai').val(0) ;
        }
        else {
            $('#nominal').hide() ;
            $('#bukanNominal').show() ;
        }
    }
    function forDipohon(catanan){
        if(catanan == 'LB' || catanan == 'PL'){
            $('#tempohBulanDiv').show() ;
            document.getElementById('tempohBulanDiv').style.visibility = "visible";

        }
        else {
            $('#tempohBulanDiv').hide() ;
            document.getElementById('tempohBulanDiv').style.visibility = "hidden";
        }
    }
    <%--jQuery.fn.ForceNumberOnly = function() {
                return this.each(function()     {
                    $(this).keydown(function(e)         {
                        var key = e.charCode || e.keyCode || 0; // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
                       return (
                        key == 8 ||
                            key == 9 ||
                            key == 46 ||
                            (key >= 37 && key <= 40) ||
                            (key >= 48 && key <= 57) ||
                            (key >= 96 && key <= 105));
                    });
                });
            };
            jQuery('.numbersOnly').ForceNumberOnly();--%>

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


</script>
<s:form  beanclass="etanah.view.stripes.pembangunan.KeputusanRPPActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Rayuan Pengurangan Premium</legend>
            <br/><br/>
            <c:if test="${edit}">
                <table align="center" cellspacing="5">

                    <tr>
                        <td><label>Bayaran Asal Yang Perlu Dijelaskan RM:</label>

                            <c:if test ="${!(actionBean.permohonanTuntutanKos.amaunSebenar eq null)}">
                                <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKos.amaunSebenar}"/>&nbsp;</td>
                            </c:if>
                            <c:if test ="${actionBean.permohonanTuntutanKos.amaunSebenar eq null}">
                                <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/>&nbsp;</td>
                            </c:if>

                    </tr>                    
                    <tr>                        
                        <td>                            
                            <label>Pengurangan Premium:</label>
                            <s:text name="permohonan.noMahkamah" id="ulasan" formatPattern="#,##0.00" size="4" maxlength="2" onkeyup="validateNumber(this,this.value);" />%
                        </td>
                    </tr>

                    <tr>
                        <td>                            
                            <label>Premium Baru(RM):</label>
                            <s:format value="${actionBean.premium}" formatPattern="#,##0.00"/>
                        </td>
                    </tr>                    
                    <tr>
                        <td>
                            <label>Ulasan <%--Butir-butir Rayuan--%> </label>
                            <s:textarea name="permohonan.rujukanUndang2" cols="90" rows="8" class="normal_text"/>
                        </td>
                    </tr>
                </table>

                <p>
                    <label>&nbsp;</label>

                    <s:button name="simpanPremium" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                </p>
            </c:if>

            <c:if test="${!edit}">
                <table align="center" cellspacing="7">
                    <tr>
                        <td>
                            <label>Bayaran Asal Yang Perlu Dijelaskan (RM):</label>
                            <c:if test ="${!(actionBean.permohonanTuntutanKos.amaunSebenar eq null)}">
                                <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKos.amaunSebenar}"/>&nbsp;</td>
                            </c:if>
                            <c:if test ="${actionBean.permohonanTuntutanKos.amaunSebenar eq null}">
                                <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/>&nbsp;</td>
                            </c:if>
                        <%--
                        <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/> &nbsp;
                        --%>
                        </td>
                    </tr>

                    <tr>
                        <td><label>Pengurangan Premium:</label>                        
                            ${actionBean.permohonan.noMahkamah}%

                        </td>
                    </tr>
                    <tr>
                        <td><label>Premium Baru (RM):</label>
                            <s:format value="${actionBean.premium}" formatPattern="#,##0.00"/>   </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Ulasan:</label>
                            ${actionBean.permohonan.rujukanUndang2}
                        </td>
                    </tr>
                </table>
            </c:if>
        </fieldset >
    </div>
</s:form>


