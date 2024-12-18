<%-- 
    Document   : stage_urusan
    Created on : Aug 25, 2014, 11:19:58 AM
    Author     : azwady
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


<script type="text/javascript">
    function doSimpan(e, f) {
        if (confirm('Adakah anda pasti? Sila semak data terlebih dahulu sebelum disimpan.')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&' + q;
            f.submit();
        }
    }
</script>
<%--<s:useActionBean beanclass="etanah.service.UrusanAliranService" id="service"/>--%>            
<s:form beanclass="etanah.view.utility.kod.StageUrusanActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div align="left">
                <c:set var="i" value="0"/>
                <p><label><font color="blue">Urusan : </font></label>      
                </p> &nbsp;
                <s:select name="urusalir.kodUrusan.kod" id="kodUrusan"> 
                    <s:option value="">--Sila Pilih Urusan--</s:option>
                    <s:options-collection collection="${actionBean.listNamaUrusan}" label="nama" value="kod"/>                    
                </s:select>
            </div>
        </fieldset>
    </div> 
    <div class="subtitle">
        <fieldset class="aras1">
            <div align="left">
                <p><label><font color="blue">Stage ID : </font></label>
                </p> &nbsp;
                <s:text name="stageID" id="stageID" size="30" class="normal_text" maxlength="50"
                        value="${actionBean.stageID}"/> 
            </div>
        </fieldset>
    </div> 

    <div class="subtitle">
        <fieldset class="aras1">
            <div align="left">
                <p><label><font color="blue">Sasaran Hari : </font></label>
                </p> &nbsp;
                <s:text name="bilangan" id="bilangan" size="30" class="normal_text" maxlength="50"/>
                <td> <fmt:formatNumber  pattern="0" value="${actionBean.sasarHari}"/>
            </div>
        </fieldset>
    </div> 
                <div class="subtitle">
                <fieldset class="aras1">
                    <p><label><font color="blue">Dimasukkan Oleh : </font></label>
                    </p> &nbsp;
                    <s:text name="dimasukoleh" id="dimasukoleh" size="30" class="normal_text" maxlength="50"
                            value="${actionBean.infoAudit.dimasukOleh}"/>
                </fieldset>
            </div>
                <div class="subtitle">
                     <fieldset class="aras1">
    <div>
        <p align="centre" ><s:submit name="getSimpan" class="btn" value="SIMPAN" onclick="doSimpan(this.form, this.name)"/></p>
    </div>
            </fieldset>
    </div>
</s:form>

