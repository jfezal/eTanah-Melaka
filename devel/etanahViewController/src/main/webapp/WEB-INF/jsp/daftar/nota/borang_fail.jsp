<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.stripes.nota.BorangActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Rujukan Fail</legend>
<!--            <p style="color:red">
                *Isi Yang Berkenaan Sahaja.
            </p>-->
          
                <p>
                    <label for="noFail">No. Fail :</label>
                    <s:text name="permohonanRujLuar.noFail"/>
                </p>
                <p>
                    <label for="noRujukan">No. Rujukan Fail :</label>
                     <s:text name="permohonanRujLuar.noRujukan"/>
                </p>

                 <p>
                <label>&nbsp;</label>
                <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanNofail" value="Simpan"/>
            </p>

        </fieldset>
    </div>
</s:form>