<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function test(f) {
        $(f).clearForm();
    }

</script>
<s:form beanclass="etanah.view.stripes.common.TaskDebug">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Trace Task BPEL</legend>
            <font size="1" color="Red">Sila masukkan ID Permohonan / ID Perserahan untuk carian.</font>
            <p>
                <label><font color="red">*</font>ID Permohonan / ID Perserahan :</label>
                    <s:text name="id_mohon"/>
                    <s:submit name="trace" id="trace" value="Cari" class="btn"/>
            </p>
            <p>
                <label>Stage :</label>
                <s:text name="stage"/>
            </p>
            <p>
                <label>Tindakan :</label>
                <s:text name="tindakan"/>
            </p>
            <p>
                <label>Task ID :</label>
                <s:text name="task_id"/>
            </p>
            <p>
                <label>Task Number :</label>
                <s:text name="task_number"/>
            </p>
            <p>
                <label>Participant :</label>
                <s:text name="participant"/>
            </p>
            <p>
                <label>Acquired By :</label>
                <s:text name="acquired_by"/>
            </p>
            <p>
                <label>Task State :</label>
                <s:text name="task_state"/>
            </p>
             <p>
                <label>Task State :</label>
                <s:text name="creator"/>
            </p>
        </fieldset>
        <fieldset class="aras1">
            <legend>Trace List Task BPEL</legend>
            <display:table name="${actionBean.lis}" class="tablecloth">
                <display:column title="Stage" property="stage"/>
                <display:column title="tindakan" property="tindakan"/>
                <display:column title="task_id" property="task_id"/>
                <display:column title="task_number" property="task_number"/>
                <display:column title="participant" property="participant"/>
                <display:column title="acquired_by" property="acquired_by"/>
                                <display:column title="task_state" property="task_state"/>
                                                                <display:column title="creator" property="creator"/>


            </display:table>
            
        </fieldset>
    </div>
</s:form>
