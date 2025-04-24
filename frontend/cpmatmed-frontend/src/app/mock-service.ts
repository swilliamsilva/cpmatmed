
// src/app/shared/mock-service.ts
export class MockService {
  getAll() {
    return { subscribe: (cb: any) => cb([]) };
  }

  getById(id: number) {
    return { subscribe: (cb: any) => cb({ id }) };
  }

  create(data: any) {
    return { subscribe: (cb: any) => cb(data) };
  }

  update(id: number, data: any) {
    return { subscribe: (cb: any) => cb({ ...data, id }) };
  }

  delete(id: number) {
    return { subscribe: (cb: any) => cb(null) };
  }
}

