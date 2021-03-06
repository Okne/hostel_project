package war.webapp.service.impl;

import war.webapp.service.DutyListLoadService;
import war.webapp.service.builder.BaseDutyListTemplateBuilder;
import war.webapp.service.impl.builder.ExcelDutyListTemplateBuilder;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class HttpDutyListLoadService extends DutyListLoadService {

    @Override
    public void download(Object... params) throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
                .getResponse();
        byte[] dataForDownload = getDutyListTemplateBuilder().build(params);

        String fileName = generateDataDescription(params);
        int pz = fileName.lastIndexOf('/');
        String shortFileName = fileName.substring(pz + 1);

        response.setContentLength((int) dataForDownload.length);
        setResponseContentType(response);
        response.setContentType("application/excel");
        response.setDateHeader("Expires", System.currentTimeMillis());
        response.setHeader("Cache-Control", "must-revalidate");
        response.setHeader("Accept-Ranges", "none");
        response.setDateHeader("Last-Modified", Calendar.DATE);
        response.setHeader("Content-disposition", "attachment;filename=" + shortFileName);

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(dataForDownload);
        out.flush();
        FacesContext.getCurrentInstance().responseComplete();
    }

    private void setResponseContentType(HttpServletResponse response) {
        if (getDutyListTemplateBuilder() instanceof ExcelDutyListTemplateBuilder) {
            response.setContentType("application/excel");
        } else {
            response.setContentType("application/ostet-stream");
        }
    }

    @Override
    public BaseDutyListTemplateBuilder getDutyListTemplateBuilder() {
        return new ExcelDutyListTemplateBuilder();
    }

    @Override
    public String generateDataDescription(Object... params) {
        StringBuffer buff = new StringBuffer("report_");
        buff.append(params[0]);
        //TODO make correct encoding or mapping of monthes
//        buff.append("_");
//        buff.append(params[1]);
        buff.append("-floor");
        buff.append(".xls");
        return buff.toString();
    }

}
