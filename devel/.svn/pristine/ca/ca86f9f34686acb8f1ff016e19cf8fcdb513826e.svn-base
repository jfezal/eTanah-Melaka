<%-- 
    Document   : perakuanWaran
    Created on : May 27, 2011, 4:17:37 PM
    Author     : faidzal
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function save(event, f){
        
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            $('#page_div').html(data);

        },'html');

    }
    
    function validate(){
        var kpsn1 = document.getElementById("kpsn1");
        var kpsn2 = document.getElementById("kpsn2");
        if (kpsn1.checked || kpsn2.checked)
        {
            return true;
        }else{
            alert('Sila Masukkan Keputusan.');
            document.getElementById("kpsn1").focus();
            return false;
                
        }

    }
</script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%--original <s:form name="form1" beanclass="etanah.view.strata.PerakuanKeputusanWaranActionBean">--%>
<s:form beanclass="etanah.view.strata.PerakuanKeputusanWaranActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>${actionBean.legend}</legend>
            <s:hidden name="idStor"/>
            <c:if test="${actionBean.negeri eq '05'}"> 
                <p>
                    <label>Keputusan</label><s:radio name="keputusanRadio" id="kpsn1" value="L" />&nbsp;Lulus
                    <s:radio name="keputusanRadio" id="kpsn2"  value="T"/>&nbsp;Tolak
                </p>
            </c:if>
            <p>

                <label> Perakuan :</label><s:textarea name="perakuan" id="perakuan" class="normal_text" readonly="${actionBean.readOnly}" cols="80"/>
            </p>
            <c:if test="${!actionBean.readOnly}">
                <p>
                    <c:if test="${actionBean.negeri eq '05'}"> 
                    <label>&nbsp;</label> <s:button name="savePerakuan" value="Simpan" class="btn" onclick="if (validate()){doSubmit(this.form,this.name,'page_div');}" />
                    </c:if>
                    <c:if test="${actionBean.negeri eq '04'}"> 
                    <label>&nbsp;</label> <s:button name="savePerakuan" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')" />
                    </c:if>
                </p>
            </c:if>  
        </fieldset>
    </div>
</s:form>

