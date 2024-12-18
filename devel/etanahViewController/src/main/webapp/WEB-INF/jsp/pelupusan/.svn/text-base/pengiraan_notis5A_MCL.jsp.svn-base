<%-- 
    Document   : pengiraan_notis5A_MCL
    Created on : Sept 28, 2010, 4:16:35 PM
    Author     : afham
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
    //    function validate(){
    //        var a = document.kira.tahunS ;
    //        if(a.value == ""){
    //            alert("Sila masukkan tahun semasa") ;
    //            return false ;
    //        }
    //        return true ;
    //    }

    //    function calc(value){
    //        var a = document.kira.tahunP.value ;
    //        var nP = document.kira.nilaiP.value ;
    //        var total ;
    //        var nP1 ;
    //        var nP2 ;
    //        var nP3 ;
    //        var nP4 ;
    //        total = a - value ;
    //        nP1 = parseFloat(nP) * total / 100 ;
    //        nP2 = parseFloat(nP) - nP1 ;
    //        nP3 = nP2 / 4 ;
    //        nP4 = nP3 / 2 ;
    //        $("#btp").val(total);
    //        $("#btp1").val(total);
    //        $("#bakiTempohPajakan").val(total);
    //
    //                $("#nilaiPasaran1").val(nP1);
    //                $("#np3").val(nP1);
    //
    //                $("#nilaiPasaran2").val(nP2);
    //                $("#np4").val(nP2);
    //
    //                $("#nilaiPasaran3").val(nP3);
    //                $("#np5").val(nP3);
    //
    //                $("#np6").val(nP4);
    //                $("#premium").val(nP4);
    //
    //
    //                if(nP4 != ""){
    //                    convert(nP4) ;
    //                }
    //            }
    function getHakmilikDetails(val){
        
        doBlockUI();
        var edit = $('#edit').val() ;
        var url = '${pageContext.request.contextPath}/pelupusan/pengiraan_notis_5a_mcl?searchHakmilik&idHakmilik='+val+'&edit='+edit;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
        
    }
   
    
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.PengiraanNotis5AMCLActionBean" name="kira">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset  class="aras1" id="locate">
            <legend>
                Senarai Hakmilik Terlibat
            </legend>

            <p>
                <label>Hakmiilik :</label>
                <s:select name="idHakmilik" id="idmohon" onchange="getHakmilikDetails(this.value)">
                    <c:forEach items="${actionBean.hakmilikPermohonanList}" var="line">
                        <s:option value="${line.id}">${line.hakmilik.idHakmilik}(${line.hakmilik.bandarPekanMukim.daerah.nama}-${line.hakmilik.bandarPekanMukim.nama})</s:option>
                    </c:forEach>
                </s:select>
            </p>

        </fieldset>
        <fieldset class="aras1">
            <legend>Pengiraan Notis 5A (MCL)</legend>
            <div align="content">


                <p>
                    <label>Tahun Pajakan : </label>
                    <s:text name="tahunP" readonly="true"/>
                </p>
                <p>
                    <label>Tahun Semasa : </label>
                    <s:text name="tahunS" id="tahunS" readonly="true" onkeyup="validateNumber(this,this.value);"/>
                    <s:hidden name="tahunS" value="tahunS"/>
                </p>
                <p>
                    <label>Baki Tempoh Pajakan : </label>
                    <s:text name="bakiTempohPajakan" id="bakiTempohPajakan" readonly="true" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <hr>
                <p>
                    <label>No.Lot :</label>
                    ${actionBean.hakmilik.noLot}
                </p>
                <p>
                    <label>Mukim :</label>
                    ${actionBean.hakmilik.bandarPekanMukim.nama}
                </p>
                <c:if test="${actionBean.thnhakmilik eq '96'}">
                    <label>Nilai Nominal RM :</label>
                    <s:text name="nilaiP" readonly="true" id="nilai" value="${actionBean.nilaiP}" formatPattern="#,##0.00"/>
                </c:if>
                <c:if test="${actionBean.thnhakmilik eq '95'}">

                    <p>
                        <label>Nilai Pasaran :</label>
                        RM <s:text name="nilaiP" id="nilaiP"/>
                    </p>
                    <p>
                        <label>Baki Tempoh Pajakan :</label>
                        <s:text name="bakiTempohPajakan" id="bakiTempohPajakan" readonly="true"/> Tahun
                        <s:hidden name="bakiTempohPajakan" value="bakiTempohPajakan"/>
                    </p>
                    <p>
                        <label>=</label>
                        (NP)RM <s:text name="nilaiP" id="nilaiP" formatPattern="#,##0.00"/> * <s:text name="bakiTempohPajakan" id="bakiTempohPajakan"/> / 100 <br>
                        <label>=</label> RM <s:text name="nilaiPasaran1" id="nilaiPasaran1" readonly="true" formatPattern="#,##0.00"/>
                        <s:hidden name="nilaiPasaran1" value="nilaiPasaran1"/>
                    </p>
                    <p>
                        <label>=</label>
                        (NP)RM <s:text name="nilaiP" id="nilaiP" readonly="true" formatPattern="#,##0.00"/> - <s:text name="nilaiPasaran1" readonly="true" id="nilaiPasaran1"/><br>
                        <label> = </label> RM <s:text name="nilaiPasaran2" readonly="true" id="nilaiPasaran2"/>
                        <s:hidden name="nilaiPasaran2" value="nilaiPasaran2"/>
                    </p>
                    <p>
                        <label>=</label>
                        RM <s:text name="nilaiPasaran2" id="nilaiPasaran2" readonly="true"/> / 4
                    </p>
                    <p>
                        <label>=</label>
                        RM <s:text name="nilaiPasaran3" id="nilaiPasaran3" value="nilaiPasaran3" readonly="true"/>
                        <s:hidden name="nilaiPasaran3" value="nilaiPasaran3"/>
                    </p>
                    <p>
                        <label>=</label>
                        RM <s:text name="nilaiPasaran3" id="nilaiPasaran3" readonly="true"/> / 2
                    </p>
                    <p>
                        <label>=</label>
                        RM <s:text name="np6" id="np6" readonly="true"/>

                    </p>
                    <p>
                        <label>Premium :</label>
                        RM <s:text name="premium" id="premium" readonly="true"/>
                        <s:hidden name="premium" value="premium"/>

                    </p>
                </c:if>
                <%--<p>
                    <label>&nbsp;</label>
                    <s:textarea name="namaHarga" id="namaHarga"/>
                </p>--%>

            </div>
            <%--  <s:button name="simpan" id="save" class="btn" value="Simpan" onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/></center> --%>
        </fieldset>
    </div>
</s:form>